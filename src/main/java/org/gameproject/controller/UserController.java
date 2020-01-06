package org.gameproject.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.gameproject.entities.Utilisateur;
import org.gameproject.entities.UtilisateurImage;
import org.gameproject.exception.ResourceNotFoundException;
import org.gameproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/*
 * This class is our controller, it will get every request and lead to the correct Page, using different method from UserService 
 * to save/show/update/delete/redirect/...
 */
/* 
 * @Controller is simply a specialization of the @Component class and allows implementation classes to be autodetected through the classpath scanning.
 * it's typically used in combination with a @RequestMapping annotation used on request handling methods.
 * 
 * @RequestMapping is used to map web requests to Spring Controller methods.
 */
@Controller 		
@RequestMapping("/")
public class UserController {
	
	/*
	 * We use Logger’s methods to generate log statements. Log4j provides the factory method to get Logger objects.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	/*
	 * @GetMapping is specialized version of @RequestMapping annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET) 
	 * @GetMapping annotated methods handle the HTTP GET requests matched with given URI expression.
	 * @PostMapping is the same things but for RequestMethod.POST
	 * the model can supply attributes used for rendering views.
	 * 
	 * + Main difference between POST and GET method is that GET carries request parameter appended in URL string 
	 * 	 while POST carries request parameter in message body which makes it more secure way of transferring data from client to server in http protocol
	 * 
	 * + A GET method should be used to retrieve data from the server. 
	 *	 Multiple get requests to the same URL should be valid and no data should be changed on the server side
	 * 
	 * + A POST method should be used when you need to create, update or delete data on the server side. 
	 * 	 Making the same POST request multiple times may not be safe and may result in inconsistent data. 
	 * 	 The content of a POST request is sent in the request body. Hence, you don't see the parameters in your browser
	 * 
	 * 
	 * Model theModel : To provide a view with usable data, we simply add this data to its Model object. 
	 * Additionally, maps with attributes can be merged with Model instances
	 */
	@GetMapping("/list")
	public String listUtilisateurs(Model theModel) {
		List <Utilisateur> theUser = userService.getUtilisateurs();
		theModel.addAttribute("utilisateurs", theUser);
		return "list-users";
	}
	
	@GetMapping("/showForm")
	public String showFormForAdd(Model theModel) {
		/*
		 * log.debug message will appears only during a debug launch
		 */
		LOG.debug("la j'pige pas exactement");
		Utilisateur theUser = new Utilisateur();
		theModel.addAttribute("utilisateur", theUser);
		return "user-form";
	}
	/*
	 * @ModelAttribute can be used either as a method parameter or at the method level.
	 * (Here we use it as a method parameter)
	 * it indicates the argument should be retrieved from the model. 
	 * When not present, it should be first instantiated and then added to the model and once present in the model, 
	 * the arguments fields should be populated from all request parameters that have matching names.
	 */
	@PostMapping("/saveUser")
	public String saveUtilisateur(@Valid @ModelAttribute("utilisateur") Utilisateur identifiant,Errors errors, HttpServletRequest request, 
			 @RequestParam("file") MultipartFile file, UtilisateurImage imagePath) {
		 if (errors.hasErrors()) {
		        return "redirect:/showForm?error=true";
		    }
			 
					try {
						if(file.isEmpty()) {
							
							
							
							// Creating the directory to store file
							File dir = new File("D:/gameproject_image"+File.separator+"image"
							+File.separator+ identifiant.getIdentifiant());
								if (!dir.exists()) {
									dir.mkdirs();
								}
								
								// Default location of the image by default
								File defaultFile = new File("D:\\workspace\\gameproject\\src\\main\\webapp\\resources\\image\\Me.jpg");
								
								// Récupère le Path de l'image
								Path path = Paths.get(defaultFile.toURI());
								
								// Récupère les bytes de l'image
								byte[] bytes = Files.readAllBytes(path);
								
								String name = defaultFile.getName();
									// Create the file on server
									File serverFile = new File(dir.getAbsolutePath()
											+ File.separator+name);
									
									imagePath.setImagePath(serverFile.getPath());
									
									BufferedOutputStream stream = new BufferedOutputStream(
											new FileOutputStream(serverFile));
									stream.write(bytes);
									stream.close();
						}
						if (!file.isEmpty()) {
							byte[] bytes = file.getBytes();

							String name = file.getOriginalFilename();
							
							// Creating the directory to store file
							File dir = new File("D:/gameproject_image"+File.separator+"image"
							+File.separator+ identifiant.getIdentifiant());
								if (!dir.exists()) {
									dir.mkdirs();
								}
								
								
									// Create the file on server
									File serverFile = new File(dir.getAbsolutePath()
											+ File.separator+name);
									
									// the imagePath get the path name from serverFile
									imagePath.setImagePath(serverFile.getPath());
									
									BufferedOutputStream stream = new BufferedOutputStream(
											new FileOutputStream(serverFile));
									stream.write(bytes);
									stream.close();
									LOG.info("Server File Location="
											+ serverFile.getAbsolutePath());
						}
						
						 
						userService.saveUtilisateur(identifiant, imagePath);
						
						return "redirect:/createSuccessful";
					}	catch (Exception e) {
					return "redirect:/createError";
				}
		 }
			 
	/*
	 * @RequestParam will extract query parameters, form parameters and even files from the request.
	 * we used @RequestParam to extract the utilisateurId query parameter.
	 */
	@GetMapping("/updateForm")
	public String showFormForUpdate(@RequestParam("utilisateurId") int theId, Model theModel, Principal principal) throws ResourceNotFoundException {
		
		String username = principal.getName();
		// On utilise la méthode permettant de trouver la chaine de caractère username dans la BDD en la renvoyant sous forme Utilisateur
		Utilisateur identifiant = userService.findUserByUsername(username);
		
		theModel.addAttribute("utilisateur", identifiant);
		return "updateForm";
	}
	@PostMapping("/saveUpdate")
	public String saveUpdate(@ModelAttribute("utilisateur") Utilisateur identifiant,Errors errors, 
			HttpServletRequest request, UtilisateurImage imagePath){
		 if (errors.hasErrors()) {
		        return "redirect:/userInfo/parameters?error=true";
		    }
				userService.updateUtilisateur(identifiant);
				// Disconnect the user 
				SecurityContextHolder.clearContext();
			return "redirect:/logoutUpdate";
		
	}
	@GetMapping("/updateImg")
	public String showImgForUpdate(@RequestParam("utilisateurId") int theId, Model theModel, Principal principal) throws ResourceNotFoundException {
		
		String username = principal.getName();
		// On utilise la méthode permettant de trouver la chaine de caractère username dans la BDD en la renvoyant sous forme Utilisateur
		Utilisateur identifiant = userService.findUserByUsername(username);
		
		theModel.addAttribute("utilisateurImage", identifiant);
		return "updateImgForm";
	}
	
	@PostMapping("/saveUpdateImg")
	public String saveUpdateImg(@ModelAttribute("utilisateurImage") UtilisateurImage imagePath, 
			HttpServletRequest request, @RequestParam("file") MultipartFile file, Principal principal) {
		 try {
			 // Récupère l'identifiant connecté
			 String username = principal.getName();
			 // récupère les infos de la personne
			 Utilisateur identifiant = userService.findUserByUsername(username);
			 // récupère sous forme int l'id de la personne
			 int UserId = identifiant.getId();
			 // Retrouve les données de l'image lié à l'id de cette personne
			 UtilisateurImage image = userService.findIdByUserId(UserId);
			 // Récupère l'id de l'image 
			 int imageId = image.getId();
			 
			 // donne l'id de l'utilisateur à la nouvelle image
			 imagePath.setUtilisateurs_id(UserId);
			 
			 
			 // Si l'utilisateur Valide SANS avoir ajouté d'image
			 if(file.isEmpty()) {
					
					return "redirect:/userInfo/parameters";
				}
			 	// Si l'utilisateur valide une image
			 	else {
			 		
			 		// Récupère le Path de l'image via l'id de l'utilisateur
					 String imgPath = userService.getImagePathById(UserId);
					 // Récupère l'image contenu au bout du Path
					 File deleteImg = new File(imgPath);
					 // On Supprime cette image
					 deleteImg.delete();
					 
					 // Supprime toute les informations lié à cette id
					 userService.deleteUtilisateurImage(imageId);
					byte[] bytes = file.getBytes();

					String name = file.getOriginalFilename();
					// Creating the directory to store file
					File dir = new File("D:/gameproject_image"+File.separator+"image"
					+File.separator + identifiant.getIdentifiant());
						if (!dir.exists()) {
							dir.mkdirs();
						}
						
						
						
							// Create the file on server
							File serverFile = new File(dir.getAbsolutePath()
									+ File.separator+name);
							
							imagePath.setImagePath(serverFile.getPath());
							
							
							BufferedOutputStream stream = new BufferedOutputStream(
									new FileOutputStream(serverFile));
							stream.write(bytes);
							stream.close();
							LOG.info("Server File Location="
									+ serverFile.getAbsolutePath());
							 
				}
			// Enregistre le path de la nouvelle image
			 userService.updateImage(imagePath);
		 } catch (Exception e) {
			 return "redirect:/userInfo/parameters?error=true";
		 }
			return "redirect:/logoutUpdate";
		
	}
	@GetMapping("/delete")
	public String deleteUtilisateur(@RequestParam("utilisateurId") int theId) throws ResourceNotFoundException {
		userService.deleteUtilisateur(theId);
		return "redirect:/list";
	}
	
	@GetMapping(value = { "/", "/welcome" })
	   public String welcomePage(Model model) {
	       model.addAttribute("title", "Welcome");
	       model.addAttribute("message", "Page d'accueil !");
	       return "welcomePage";
	   }
	 
	   @GetMapping("/admin")
	   public String adminPage(Model model) {
	       return "adminPage";
	   }
	 
	   @GetMapping("/login")
	   public String loginPage(Model model ) {
	      
	       return "loginPage";
	   }
	 
	   @GetMapping("/logoutSuccessful")
	   public String logoutSuccessfulPage(Model model) {
	       model.addAttribute("title", "Logout");
	       return "logoutSuccessfulPage";
	   }
	   
	   @GetMapping("/logoutUpdate")
	   public String logoutUpdate(Model model, HttpServletRequest request) {
	       model.addAttribute("title", "Mis à jour !");
	       return "logoutUpdate";
	   }
	 
	   @GetMapping("/userInfo")
	   public String userInfo(Model model, Principal principal){
		   String identifiant = principal.getName();
		   model.addAttribute("message", "Bonjour "+identifiant+" !");
		   model.addAttribute("title", "GameProject");
		   
	       return "userInfoPage";
	   }
	 
	   @GetMapping("/403")
	   public String accessDenied(Model model, Principal principal) {
	        
	       if (principal != null) {
	           model.addAttribute("message", "Bonjour " + principal.getName()
	                   + ",<br> Vous n'avez pas la permission pour accéder à cette page !");
	       } else {
	           model.addAttribute("msg",
	                   "Vous n'avez pas la permission pour accéder à cette page !");
	       }
	       return "403Page";
	   }
	   
	   @GetMapping("/createSuccessful")
	   public String createSuccessfulPage(Model model) {
		   model.addAttribute("title", "Create");
		   return "createSuccessfulPage";
	   }
	   @GetMapping("/createError")
	   public String createErrorPage(Model model) {
		   model.addAttribute("title", "Create");
		   return "createErrorPage";
	   }
	    
	   @RequestMapping(value = "/{id}")
		public String getUsertPhoto(HttpServletResponse response, @PathVariable("id") int id, Principal principal, Utilisateur identifiant) throws Exception {
			response.setContentType("image/png");
			response.setContentType("image/jpg");
			
			// On récupère le nom de la personne logged dans un String
			String username = principal.getName();
			// On utilise la méthode permettant de trouver la chaine de caractère username dans la BDD en la renvoyant sous forme Utilisateur
			identifiant = userService.findUserByUsername(username);
			// UserId récupère l'id de l'utilisateur
			int UserId = identifiant.getId();
			// Si ID de l'utilisateur = l'id de la photo alors on affiche la photo
			// Sinon renvoi à la page paramètres
			if(UserId == id) {
				String imagePath = userService.getImagePathById(id);
				   
				  
				   Path path = Paths.get(imagePath);

			byte[] data = Files.readAllBytes(path);
			InputStream inputStream = new ByteArrayInputStream(data);
			IOUtils.copy(inputStream, response.getOutputStream());
			}
			return "redirect:/userInfo/parameters";
		}
	   @GetMapping("/userInfo/parameters")
	   public ModelAndView userParameters(ModelMap mod, Principal principal, Authentication authentication, Utilisateur identifiant) throws IOException{
		   
		   ModelAndView model = new ModelAndView("/userParameters");
			
		   
		   String username = principal.getName();
		   identifiant = userService.findUserByUsername(username);
		   // On récupère le chemin de l'image via l'id de l'user
		   String imagePath = userService.getImagePathById(identifiant.getId());
		   
		   
		   Path path = Paths.get(imagePath);

		  
		   model.addObject("email", identifiant.getEmail());
		   model.addObject("identifiant", identifiant.getIdentifiant());
		   model.addObject("mdp", identifiant.getMot_de_passe());
		   model.addObject("id", identifiant.getId());
		   model.addObject("image", path);
		   
	       return model;
	   }
}
