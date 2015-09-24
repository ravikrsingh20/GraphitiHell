package org.fit.vg.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.fit.vg.model.Images;
import org.fit.vg.service.GraphitiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 * handles request for all the graphitis related calls from web application
 * @author Ravi Kumar Singh
 *
 */
@Controller
public class GraphitiController {
	private static final Logger logger = LoggerFactory.getLogger(GraphitiController.class);
	@Autowired
	private GraphitiService graphitiService;
	public GraphitiController()
	{
		System.out.println("GraphitiController initiated");
	}
	/**
	 * returns the index page to draw graphiti
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/drawGraphiti", method = RequestMethod.GET)
	public String drawGraphiti(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	
	/**
	 * rest api call from javascript to upload image to db
	 * @param req
	 * @param redir
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/upload/graphiti", method = RequestMethod.POST)
	@ResponseBody	
	public ResponseEntity<String> uploadImage(HttpServletRequest req,RedirectAttributes redir,Principal principal) {
		Images image = new Images();
		if(principal != null && !(principal.getName().equals(null)))
			image.setUserName(principal.getName());	
		String imageString = req.getParameter("image");
		imageString = imageString.substring("data:image/jpeg;base64,".length());

		byte[] decodedImage = Base64.decodeBase64( imageString.getBytes() );    		
		image.setImage(decodedImage);
		graphitiService.createGraphiti(image);
		req.getSession().setAttribute("ImageId", image.getId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	/**
	 * returns the location page to select location for a grahiti
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/location", method = RequestMethod.GET)
	public String uploadlocation(HttpServletRequest req) {		
		return "location";
	}
	
	/**
	 * rest call from js to upload the location for a the current graphiti in session
	 * @param req
	 * @param principal
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/upload/location", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> uploadlocation(HttpServletRequest req, Principal principal,HttpSession session) {
		Images image = new Images();
		Images imageret;
		String  imageId1 = req.getParameter("id");	
		String coord  = req.getParameter("coord");	
		String lng = coord.substring(1,coord.indexOf(","));
		String lat = coord.substring(coord.indexOf(",")+1,coord.indexOf(")"));
		String imageId = session.getAttribute("ImageId").toString();
		image.setId(Integer.parseInt((String)imageId));
		image.setLongitude(Double.parseDouble(lat));
		image.setLatitude(Double.parseDouble(lng));
		imageret = graphitiService.updateGraphitiLoc(image);
		if(imageret.getId() == 0 )
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		else				
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	/**
	 * returns the graphitilist page with list of all the graphitis created by the logged in user
	 * @param req
	 * @param principal
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = "/viewGraphiti", method = RequestMethod.GET)
	public String viewGraphitiList(HttpServletRequest req, Principal principal,Model model) {
		Images image = new Images();
		
		if(principal != null && !(principal.getName().equals(null))){
			image.setUserName(principal.getName());
			List <Images> imageList  = graphitiService.getGraphitiByUser(image);
			model.addAttribute("ImageList", imageList);			
		}	
		
		return "graphitilist";
	}
	
	/**
	 * returns the graphiti when logged in user clicks on graphitilist page to view a particular graphiti
	 * @param req
	 * @param principal
	 * @param model
	 * @param imageId
	 * @return
	 */
		
	@RequestMapping(value = "/graphiti", method = RequestMethod.GET)
	public String getGraphiti(HttpServletRequest req, Principal principal,Model model,@RequestParam(value = "id", required = false) Integer imageId) {
		Images image = new Images();
		
		if(principal != null && !(principal.getName().equals(null))){
			image.setUserName(principal.getName());
			image.setId(imageId);
			Images imageret  = graphitiService.getGraphitiByUserIdAndImageId(image);
			imageret.setImage(Base64.encodeBase64(imageret.getImage()));
			imageret.setImageName(new String(imageret.getImage()));
			model.addAttribute("Images", imageret);			
		}	
		return "viewGraphiti";
	}

}
