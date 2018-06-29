package com.joiest.jpf.facade;

import com.joiest.jpf.dto.ToolCateResponse;
import org.apache.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ToolCateServiceFacade {

    public Map<String,String> baseToImage(HttpServletRequest request, String imgStr, String perfix);

    public ToolCateResponse convert(HttpResponse response);
}
