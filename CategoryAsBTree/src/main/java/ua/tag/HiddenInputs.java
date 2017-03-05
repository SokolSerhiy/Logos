package ua.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HiddenInputs extends SimpleTagSupport{

	private final StringWriter sw = new StringWriter();
	private final List<String> excludes = new ArrayList<>();
	private final List<String> prefixes = new ArrayList<>();
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		PageContext pageContext = (PageContext) getJspContext();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		Map<String, String[]> map = request.getParameterMap();
		for(Entry<String, String[]> entry : map.entrySet()){
			if(!(excludes.contains(entry.getKey())||containsByPrefix(entry.getKey()))){
				for(String value : entry.getValue()){
					sw.append("<input type='hidden' ");
					sw.append("name='");
					sw.append(entry.getKey());
					sw.append("' value='");
					sw.append(value);
					sw.append("'>");
				}
			}
		}
		out.println(sw.toString());
	}
	
	private boolean containsByPrefix(String param){
		for (String prefix : prefixes){
			if(param.startsWith(prefix)){
				return true;
			}
		}
		return false;
	}
	
	public void setExcludeParams(String excludeParams){
		StringTokenizer tokenizer = new StringTokenizer(excludeParams, ", ");
		while(tokenizer.hasMoreTokens()){
			excludes.add(tokenizer.nextToken());
		}
	}
	
	public void setExcludeParamsPrefix(String prefix){
		StringTokenizer tokenizer = new StringTokenizer(prefix, ", ");
		while(tokenizer.hasMoreTokens()){
			prefixes.add(tokenizer.nextToken());
		}
	}
}
