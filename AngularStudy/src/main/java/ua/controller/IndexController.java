package ua.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping(value={"/", "/admin/ingredient", "/admin/country", "/admin/recipe", "/admin/amount", "/admin/ms"}, headers={"Accept=text/html"})
	public void index(HttpServletResponse response) throws IOException{
		InputStream is = new FileInputStream(new File(System.getProperty("catalina.home")+"/angular/views/index.html"));
	    BufferedInputStream inputStream = new BufferedInputStream(is);
	    int nRead;
	    byte[] b = new byte[10000];
	    ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
	    while((nRead= inputStream.read(b,0,b.length)) != -1){
	        arrayOutputStream.write(b,0,nRead);
	    }
	    arrayOutputStream.flush();
	    byte[] bn =arrayOutputStream.toByteArray();
	    response.setContentType("text/html");
	    response.setContentLength(bn.length);
	    OutputStream os = response.getOutputStream();
	    os.write(bn);
	    os.flush();
	    os.close();
	    inputStream.close();
	}
}
