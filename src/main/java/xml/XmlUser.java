/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import form.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Iceman
 */
public class XmlUser implements Xml{
    private List<User> userList;
    private String file;

    public List<User> getUserList() {
        return userList;
    }
    
    public XmlUser(){        
        String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");
        file = dataDirectory + "user.xml";
        userList = new ArrayList<User>();
        read();
    }
    
    public void read(){
        try {
            userList = new ArrayList<User>();
            
            File fXmlFile = new File(file);
            System.out.println("Loading file \"" + file +"\"...\n");

            //set up to read in XML file
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            //set up node list of entries
            NodeList nPostList = doc.getElementsByTagName("user");

           //go through all the entries
            for (int i = 0 ; i < nPostList.getLength(); i++){
               Node nNode = nPostList.item(i);
               Element eElement = (Element) nNode;
               String username = eElement.getElementsByTagName("username").item(0).getTextContent();
               String password = eElement.getElementsByTagName("password").item(0).getTextContent();
               userList.add(new User(username, password));
               }                  
        }
        catch (IOException ioex) {
           System.out.println("Error in Opening XML file! shutting down program");
           return;
        }
        catch (Exception ex){
           System.out.println("Error in parsing XML file! shutting down program");
           return;
        }       
    }
    
    public void addUser(User post){
        userList.add(post);
    }
    
    public void write(){
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("userInfo");
            doc.appendChild(rootElement);
            
            for(int i = 0; i < userList.size(); i++)
            {
                // post elements
                Element post = doc.createElement("user");
                rootElement.appendChild(post);

                // username elements
                Element username = doc.createElement("username");
                username.appendChild(doc.createTextNode(userList.get(i).getUsername()));
                post.appendChild(username);

                // lastname elements
                Element text = doc.createElement("password");
                text.appendChild(doc.createTextNode(userList.get(i).getPassword()));
                post.appendChild(text);
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(file));
            transformer.transform(source, result);
        }
        catch (Exception ex){
            System.out.println("Error in writing XML file! shutting down program");
        } 
    }
}
