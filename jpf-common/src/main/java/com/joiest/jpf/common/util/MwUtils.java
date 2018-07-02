/*
package com.joiest.jpf.common.util;

public class MwUtils {


    */
/**
     * 公共类
     *//*

    public class CHttpPost{

        private int sameErrorCount = 0;//相同内容发送失败次数计数
        */
/**
         * 短信息发送接口（相同内容群发，可自定义流水号）
         * @param strPtMsgId 平台返回的流水号
         * @param strUserId  帐号
         * @param strPwd 密码
         * @param strMobiles 手机号
         * @param strMessage 短信内容
         * @param strSubPort 扩展子号
         * @param strUserMsgId 用户自编流水号
         * @return 0:成功 非0:返回webservice接口返回的错误代码
         *//*

        public int SendSms(StringBuffer strPtMsgId, String ip,String port,String strUserId, String strPwd, String strMobiles, String strMessage, String strSubPort, String strUserMsgId)
        {
            int returnInt=-200;//定义返回值变量
            try {
                String result = null;//存放解析后的返回值
                Params p = new Params();
                p.setUserId(strUserId);//设置账号
                p.setPassword(strPwd);//设置密码
                p.setPszMobis(strMobiles);//设置手机号码
                p.setPszMsg(strMessage);//设置短信内容
                p.setIMobiCount(String.valueOf(strMobiles.split(",").length));//设置手机号码个数
                p.setPszSubPort(strSubPort);//设置扩展子号
                p.setMsgId(strUserMsgId);//设置流水号
                String Message = executePost(p, "http://"+ip+":"+port+"/MWGate/wmgw.asmx/"+
                        "MongateSendSubmit");//调用底层POST方法提交
                //请求返回值不为空，则解析返回值
                if(Message != null&& Message != "")
                {
                    Document doc=DocumentHelper.parseText(Message);
                    Element el = doc.getRootElement();
                    result = el.getText();//解析返回值
                } //处理返回结果
                if(result != null&& !"".equals(result)&&result.length()>10){
                    //解析后的返回值不为空且长度大于10，则是提交成功
                    returnInt=0;
                    strPtMsgId.append(result);
                }else if(result==null||"".equals(result)){//解析后的返回值为空，则提交失败
                    strPtMsgId.append(returnInt);
                }else{//解析后的返回值不为空且长度不大于10，则提交失败，返回错误码				                       				returnInt=Integer.parseInt(result);
                    strPtMsgId.append(returnInt);
                }
            } catch (Exception e) {
                sameErrorCount=sameErrorCount+1; //发送失败,发送失败次数加1
                returnInt=-200;
                strPtMsgId.append(returnInt);
                e.printStackTrace();//异常处理
            }
            return returnInt;//返回值返回
        }

        private int diffErrorCount = 0; //不同内容发送失败次数计数
        */
/**
         * 短信息发送接口（不同内容群发，可自定义不同流水号，自定义不同扩展号）
         * @param strPtMsgId 平台返回的流水号
         * @param strUserId 帐号
         * @param strPwd 密码
         * @param MultixMt 批量请求包
         * @return 0:成功 非0:返回webservice接口返回的错误代码
         *//*

        public int SendMultixSms(StringBuffer strPtMsgId,String ip,String port, String strUserId, String strPwd, List<MULTIX_MT> MultixMt)
        {
            int returnInt=-200;//返回值
            try {
                String result = null;
                StringBuffer multixmt =new StringBuffer();//批量请求包字符串
                Params p = new Params();
                p.setUserId(strUserId);//设置账号
                p.setPassword(strPwd);//设置密码
                for(int j=0;j<MultixMt.size();j++)
                {//循环组装批量请求包
                    MULTIX_MT multixMt = MultixMt.get(j);
                    multixmt.append(multixMt.getStrUserMsgId()).append("|");//设置流水号
                    multixmt.append(multixMt.getStrSpNumber()).append("|");//设置通道号
                    multixmt.append(multixMt.getStrMobile()).append("|");//设置手机号码
                    String strBase64Msg = new			String(Base64.encodeBase64(multixMt.getStrBase64Msg().getBytes("GBK")));//设置短信内容
                    multixmt.append(strBase64Msg).append(",");
                }
                String Multixmt = multixmt.substring(0,multixmt.length()-1);//截取最后一个逗号
                p.setMultixmt(Multixmt);//设置批量请求包
                String Message = executePost(p, "http://"+ip+":"+port+"/MWGate/wmgw.asmx/"+
                        "MongateMULTIXSend");//调用发送Post方法请求网关
                if(Message != null&& Message != "")//返回值不为空，则解析返回值
                {
                    Document doc=DocumentHelper.parseText(Message);
                    Element el = doc.getRootElement();
                    result = el.getText();//解析返回值
                } //处理返回结果
                if(result != null&& !"".equals(result)&&result.length()>10)
                {//解析后的返回值不为空，且长度大于10，则代表提交成功
                    returnInt=0;
                    strPtMsgId.append(result);
                }else if(result==null||"".equals(result)){//解析后的返回值为空，代表提交失败
                    strPtMsgId.append(returnInt);
                }else{//解析后的返回值不为空，且长度不大于10，则代表提交失败，会返回错误码
                    returnInt=Integer.parseInt(result);
                    strPtMsgId.append(returnInt);
                }

            } catch (Exception e) {
                diffErrorCount=diffErrorCount+1; //发送失败,发送失败次数加1
                returnInt=-200;
                strPtMsgId.append(returnInt);
                e.printStackTrace();//异常处理
            }
            return returnInt;//返回返回值
        }


        private int balanceErrorCount = 0; //查询余额失败次数计数
        */
/**
         * 查询余额接口
         * @param nBalance 帐号费用
         * @param strUserId 帐号
         * @param strPwd 密码
         * @return 0:成功 非0:返回webservice接口返回的错误代码
         *//*

        public int QueryBalance(StringBuffer nBalance, String ip,String port,String strUserId, String strPwd)
        {
            Integer result=null;//返回值
            try {
                Params p = new Params();
                p.setUserId(strUserId);//设置账号
                p.setPassword(strPwd);//设置密码
                String Message = executePost(p, "http://"+ip+":"+port+"/MWGate/wmgw.asmx/"+
                        "MongateQueryBalance");//查询余额接口POST请求
                if(Message != null&& Message != "")
                {//网关返回值不为空，则解析网关返回值
                    Document doc=DocumentHelper.parseText(Message);
                    Element el = doc.getRootElement();
                    result = Integer.parseInt(el.getText());//解析返回值
                }
            }catch(Exception e) {
                balanceErrorCount=balanceErrorCount+1; //查询余额失败，查询余额失败次数加1
                System.out.println("出现了异常，访问地址配置错误！");
                e.printStackTrace();//异常处理
            }
            if(result==null){//返回值为空，则代表查询余额失败
                nBalance.append("-1");
                result=-1;
            }else if(result>=0){//返回值大于等于0，则代表查询余额成功
                nBalance.append(result);
                result=0;
            }else{//返回值为非0，则代表查询余额失败，返回值错误码
                nBalance.append(result);
            }
            return result.intValue();//返回返回值
        }


        private int moErrorCount= 0; //查询余额失败次数计数
        */
/**
         * 获取上行
         * @param ip IP地址
         * @param port 端口
         * @param strUserId 帐号
         * @param strPwd 密码
         * @return 返回上行集合
         *//*

        public List<MO_PACK> GetMo(String ip,String port,String strUserId, String strPwd)
        {
            List<MO_PACK> moPackList=null;//返回值定义
            try {
                String[] result = null;
                Params p = new Params();
                p.setUserId(strUserId);//设置账号
                p.setPassword(strPwd);//设置密码
                p.setIReqType("1");//设置为1，获取上行
                List<String> Lists = new ArrayList<String>();//用于存放上行
                String Message =null;//存放网关返回值字符串定义
                Message = executePost(p, "http://"+ip+":"+port+"/MWGate/wmgw.asmx/"+
                        "MongateGetDeliver");//调用POST方法请求网关
                if(Message != null&& Message !=""&& Message.contains("<string>"))
                {//网关返回值不为空，则解析返回值
                    Document doc=DocumentHelper.parseText(Message);
                    Element el = doc.getRootElement();
                    Iterator it = el.elementIterator();
                    while(it.hasNext())
                    {//循环解析返回值
                        Element elm = (Element) it.next();
                        Lists.add(elm.getText());//将上行记录添加到LIST集合中
                    }
                    if(Lists != null&& Lists.size() >0)
                    {//上行放到字符串数组中
                        result = new String[Lists.size()];
                        for(int i=0;i<Lists.size();i++)
                        {
                            result[i]= Lists.get(i);
                        }
                    }
                }//处理返回结果
                if(result != null){
                    moPackList=new ArrayList<MO_PACK>();
                    for (int i = 0; i < result.length; i++)
                    {//循环解析字符串数组
                        String[] resultArr=result[i].split(",");//以逗号分割
                        if(resultArr.length>=7){
                            MO_PACK moPack=new MO_PACK();//MO对象
                            moPack.setStrMoTime(resultArr[1]);//设置上行时间
                            moPack.setStrMobile(resultArr[2]);//设置上行手机号码
                            moPack.setStrSpNumber(resultArr[3]);//设置上行通道号
                            moPack.setStrExNo(resultArr[4]);//设置上行扩展号
                            moPack.setStrReserve(resultArr[5]);//设置预留字段
                            //处理上行信息，信息中可能出现英文逗号
                            int start=0;
                            int index=0;
                            while(index<6){
                                start+=resultArr[index].length()+1;
                                index++;
                            }
                            moPack.setStrMessage(result[i].substring(start, result[i].length()));//设置上行内容
                            moPackList.add(moPack);//将上行添加到集合中去
                        }//数组长度大于等于7才算合法
                    }
                }
            } catch (Exception e) {
                moErrorCount=moErrorCount+1; //查询余额失败次数计数
                System.out.println("出现了异常，访问地址配置错误！");
                e.printStackTrace();//处理异常
            }
            return moPackList;//返回返回值
        }


        private int rptErrorCount= 0; //获取状态报告失败次数计数
        */
/**
         * 状态报告
         * @param ip IP地址
         * @param port 端口
         * @param strUserId 帐号
         * @param strPwd 密码
         * @return 返回状态报告集合
         *//*

        public List<RPT_PACK> GetRpt(String ip,String port,String strUserId, String strPwd)
        {
            List<RPT_PACK> rptPackList=null;//返回值定义
            try {
                String[] result = null;
                Params p = new Params();
                p.setUserId(strUserId);//设置账号
                p.setPassword(strPwd);//设置密码
                p.setIReqType("2");//设置为2，获取状态报告

                List<String> Lists = new ArrayList<String>();//存放状态报告集合
                String Message =null;
                //通过POST向网关请求
                Message = executePost(p, "http://"+ip+":"+port+"/MWGate/wmgw.asmx/"+
                        "MongateGetDeliver");
                //处理返回结果
                if(Message != null&& Message !=""&& Message.contains("<string>"))
                {//网关返回值不为空，则解析网关返回值
                    Document doc=DocumentHelper.parseText(Message);
                    Element el = doc.getRootElement();
                    Iterator it = el.elementIterator();
                    while(it.hasNext())
                    {//循环将状态报告记录，放到LIST集合中
                        Element elm = (Element) it.next();
                        Lists.add(elm.getText());
                    }
                    if(Lists != null&& Lists.size() >0)
                    {//循环将状态报告记录，放到字符串数组中
                        result = new String[Lists.size()];
                        for(int i=0;i<Lists.size();i++)
                        {
                            result[i]= Lists.get(i);
                        }
                    }
                }
                if(result != null)
                {
                    rptPackList=new ArrayList<RPT_PACK>();
                    for (int i = 0; i < result.length; i++)
                    {//循环封装状态报告集合
                        String[] resultArr=result[i].split(","); //通过逗号分割
                        RPT_PACK rptPack=new RPT_PACK();
                        rptPack.setStrMoTime(resultArr[1]);//设置状态报告时间
                        rptPack.setStrPtMsgId(resultArr[2]);//设置状态报告流水号
                        rptPack.setStrSpNumber(resultArr[3]);//设置状态报告通道号
                        rptPack.setStrMobile(resultArr[4]);//设置状态报告手机号码
                        rptPack.setStrUserMsgId(resultArr[5]);//设置自定义流水号
                        rptPack.setStrReserve(resultArr[6]);//设置预留字段
                        rptPack.setnStatus(Integer.parseInt(resultArr[7]));//设置发送状态
                        rptPack.setStrErCode(resultArr[8]);//设置错误码
                        rptPackList.add(rptPack);//将状态报告添加到集合
                    }
                }

            } catch (Exception e) {
                rptErrorCount=rptErrorCount+1; //获取状态报告失败,获取状态报告失败次数加1
                System.out.println("出现了异常，访问地址配置错误！");
                e.printStackTrace();//异常处理
            }
            return rptPackList;//返回返回值
        }



        */
/**
         * 使用post请求
         * @param obj  请求参数对象
         * @param httpUrl  请求URL地址
         * @return 请求网关的返回值
         * @throws Exception
         *//*

        private String executePost(Object obj, String httpUrl) throws Exception {
            String result = "";
            Class cls = obj.getClass();
            Field[] fields = cls.getDeclaredFields();
            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            //设置请求参数
            String fieldName = null;
            String fieldNameUpper = null;
            Method getMethod = null;
            String value = null;
            for (int i = 0; i < fields.length; i++)   {//循环设置请求参数
                fieldName = fields[i].getName();
                fieldNameUpper = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                getMethod = cls.getMethod("get" + fieldNameUpper);//通过反射获取get方法
                value = (String) getMethod.invoke(obj);//通过反射调用get方法
                if(value != null) {//请求参数值不为空，才设置
                    params.add(new BasicNameValuePair(fieldName, value));
                }
            }
            HttpPost httppost = new HttpPost(httpUrl);//设置HttpPost
            httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8)); // 设置参数的编码UTF-8
            HttpClient httpclient = new DefaultHttpClient();//创建HttpClient
            HttpEntity entity = httpclient.execute(httppost).getEntity();//Http请求网关
            if(entity != null&& entity.getContentLength() >0) {//返回值不为空，且长度大于0
                result=EntityUtils.toString(entity);//将返回值转换成字符串
            }//处理返回结果
            httpclient.getConnectionManager().shutdown();//关闭连接
            return result;//返回返回值
        }

    }
}
*/
