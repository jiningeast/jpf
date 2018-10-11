package com.joiest.jpf.charge.api.controller;

import com.joiest.jpf.dto.GetValueRequest;
import com.joiest.jpf.dto.GetValueResponse;
import com.joiest.jpf.entity.OrderInfo;
import com.joiest.jpf.entity.OrderYinjiaApiInfo;
import com.joiest.jpf.entity.OrdersInfo;
import com.joiest.jpf.entity.ProductInfo;
import com.joiest.jpf.facade.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {

    public void main(String[] args){
        //this.getProperties();
    }
}
