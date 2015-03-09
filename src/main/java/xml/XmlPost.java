/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import form.Post;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
public class XmlPost implements Xml{
    private LinkedList<Post> postList;
    private String file;

    public List<Post> getPostList() {
        return postList;
    }
    
    public XmlPost(){
        String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");
        file = dataDirectory + "data.xml";
        postList = new LinkedList<Post>();
        read();
    }
    
    public void read(){
        try {
            postList = new LinkedList<Post>();
            
            File fXmlFile = new File(file);
            System.out.println("Loading file \"" + file +"\"...\n");

            //set up to read in XML file
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            //set up node list of entries
            NodeList nPostList = doc.getElementsByTagName("post");

           //go through all the entries
            for (int i = 0 ; i < nPostList.getLength(); i++){
               Node nNode = nPostList.item(i);
               Element eElement = (Element) nNode;
               String username = eElement.getElementsByTagName("username").item(0).getTextContent();
               String text = eElement.getElementsByTagName("text").item(0).getTextContent();
               String date = eElement.getElementsByTagName("date").item(0).getTextContent();
               postList.add(new Post(username, text, new Date(date)));
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
    
    public void addPost(Post post){
        postList.addFirst(post);
    }
    
    public void write(){
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("forum");
            doc.appendChild(rootElement);
            
            for(int i = 0; i < postList.size(); i++)
            {
                // post elements
                Element post = doc.createElement("post");
                rootElement.appendChild(post);

                // username elements
                Element username = doc.createElement("username");
                username.appendChild(doc.createTextNode(postList.get(i).getUsername()));
                post.appendChild(username);

                // text elements
                Element text = doc.createElement("text");
                text.appendChild(doc.createTextNode(postList.get(i).getText()));
                post.appendChild(text);
                
               // date elements
                Element date = doc.createElement("date");
                date.appendChild(doc.createTextNode(postList.get(i).getDate().toString()));
                post.appendChild(date);
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