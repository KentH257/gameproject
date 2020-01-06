package org.gameproject.authentication;

import java.util.ArrayList;
import java.util.List;

import org.gameproject.entities.Utilisateur;
import org.gameproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/* 
 * @Service marks  the java class as a bean
 * more efficiency than @Component for that type of class because it specifies intent better
 * + as a "business logic" class => this is where the enterprise(business) makes their  specific settings(logic)
 * more information on https://dev.to/tiffany/what-is-business-logic-1gdf
 *
 */
@Service
public class MyDBAuthenticationService implements UserDetailsService {
 
	
	//@Autowired eliminating the need for getters and setters
    @Autowired
    private UserService userService;
 
    /*
     * 
     * @Override Define a parent class that implement an interface, 
     *  gives an easier reading and the compiler will autocheck if the method(here loadUserByUserName) is correctly used
     *
     * This method seach the username ( with user.Service.findUtilisateur(identifiant))
     * If not find, it will send an UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String identifiant) throws UsernameNotFoundException {
        Utilisateur utilisateur = userService.findUserByUsername(identifiant);
        System.out.println("Utilisateur= " + utilisateur);
 
        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur " + identifiant + " was not found in the database");
        }
         
        // Get the [USER,ADMIN,..] of the username
        List<String> roles= userService.getUserRoles(identifiant);
         
        List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
        if(roles!= null)  {
            for(String role: roles)  {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                grantList.add(authority);
            }
        }        
         // userDetails get all information (identifiant, Mdp, and the role !)
        UserDetails userDetails = (UserDetails) new User(utilisateur.getIdentifiant(), //
                utilisateur.getMot_de_passe(),grantList);
 
        return userDetails;
    }
     
}