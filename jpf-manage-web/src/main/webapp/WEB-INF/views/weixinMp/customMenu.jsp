<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>自定义菜单</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
   <%-- <%@ include file="/WEB-INF/views/common/header_js.jsp" %>--%>
</head>
<body>

<script>

    var menuInfo = null;
    var wxMenu= '${weixinMenuInfo}';
    if(wxMenu == ''){
       wxMenu = '{"menu":{"button":[]}}'
    }
    menuInfo = JSON.parse(wxMenu);
    var cusMenu = menuInfo==null?null:menuInfo;
    var mpid = ${weixinMpInfo.id};
</script>
<link rel="stylesheet" href="${basePath}/resources/weixin/css/bootstrap.min.css">
<link rel="stylesheet" href="${basePath}/resources/weixin/css/font-awesome.min.css">
<!-- 自定义样式 -->
<link rel="stylesheet" href="${basePath}/resources/weixin/css/wx-custom.css">
<!--[if lt IE 9]>
<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

<![endif]-->
<%--<script src="${basePath}/resources/weixin/googlegg.js"></script>--%>
<div class="container">
    <div class="custom-menu-edit-con">
        <div class="hbox">
            <div class="inner-left">
                <div class="custom-menu-view-con">
                    <div class="custom-menu-view">
                        <div class="custom-menu-view__title">${weixinMpInfo.name}</div>
                        <div class="custom-menu-view__body">
                            <div class="weixin-msg-list">
                                <ul class="msg-con"></ul>
                            </div>
                        </div>
                        <div id="menuMain" class="custom-menu-view__footer">
                            <div class="custom-menu-view__footer__left"></div>
                            <div class="custom-menu-view__footer__right" ></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="inner-right">
                <div class="cm-edit-after">
                    <div class="cm-edit-right-header b-b"><span id="cm-tit"></span> <a id="delMenu" class="pull-right" href="javascript:;">删除菜单</a></div>
                    <form class="form-horizontal wrapper-md" name="custom_form">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" style="padding-left: 9px">菜单名称:</label><div class="col-sm-5">
                            <input name="custom_input_title" type="text" class="form-control" ng-model="menuname" value="" placeholder="" ng-maxlength="5"></div>
                            <div class="col-sm-5 help-block">

                                <div id="namelimit" ng-show="custom_form.custom_input_title.$dirty&&custom_form.custom_input_title.$invalid-maxlength"></div>
                                <div class="font_sml" style="display: none;">若无二级菜单，可输入20个汉字或60个字符</div><%--二级：字数不超过8个汉字或16个字母  一级：字数不超过4个汉字或8个字母--%>
                            </div>
                        </div>
                        <div class="form-group" id="radioGroup">
                            <label class="col-sm-2 control-label" style="padding-left: 9px">菜单内容:</label>
                            <div class="col-sm-10 LebelRadio">
                                <%--<label class="checkbox-inline"><input type="radio" name="radioBtn" value="sendmsg"> 发送消息</label>--%>
                                <label class="checkbox-inline" style="padding-left: 1px"><input type="radio" name="radioBtn" value="link" checked> 跳转网页</label>
                            </div>
                        </div>
                    </form>
                    <%--<div class="cm-edit-content-con" id="editMsg">
                        <div class="editTab">
                            <div class="editTab-heading">
                                <ul class="msg-panel__tab">
                                    <li class="msg-tab_item on">
                                        <span class="msg-icon msg-icon-tuwen"></span>
                                        图文消息
                                    </li>
                                </ul>
                            </div>
                            <div class="editTab-body">
                                <div class="msg-panel__context">
                                    <div class="msg-context__item">
                                        <div class="msg-panel__center msg-panel_select"  data-toggle="modal" data-target="#selectModal"><span class="message-plus">+</span><br>从素材库中选择</div>
                                    </div>
                                    <div class="msg-template"></div>
                                </div>
                            </div>
                        </div>
                    </div>--%>
                    <div class="cm-edit-content-con" id="editPage">
                        <div class="cm-edit-page">
                            <div class="row">
                                <label class="col-sm-6 control-label" style="text-align: left;padding-left: 9px;">粉丝点击该菜单会跳转到以下链接:
                                </label>
                            </div>
                            <div class="row">
                                <label class="col-sm-2 control-label" style="text-align: left;width:19%">页面地址:
                                </label>
                                <div class="col-sm-5" style="width:60%">
                                    <input type="text" name="url" class="form-control" <%--placeholder="认证号才可手动输入地址"--%>>
                                    <span class="help-block">必填,必须是正确的URL格式</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cm-edit-before"><h5>点击左侧菜单进行操作</h5></div>
            </div>
        </div>
    </div>
    <div class="cm-edit-footer">
        <button id="sortBtn" type="button" class="btn btn-default">菜单排序</button>
        <button id="sortBtnc" type="button" class="btn btn-default">完成排序</button>
        <button id="saveBtns" type="button" class="btn btn-info1">保存并发布</button>
    </div>
</div>

<script src="${basePath}/resources/weixin/js/jquery-1.11.1.min.js"></script>
<script src="${basePath}/resources/js/dialog.js"></script>

<script src="${basePath}/resources/weixin/js/bootstrap.min.js"></script>
<!-- 自定义菜单排序 -->
<script src="${basePath}/resources/weixin/js/Sortable.js"></script>
<script src="${basePath}/resources/weixin/js/menu.js?ts=234"></script>
<%--
<div id="selectModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span>×</span></button>
                <h4 class="modal-title">
                    选择图片
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div id="col_1" class="col-xs-4">
                        <div class="panel panel-default">
                            <div class="panel-heading msg-date">
                                08月12日
                            </div>
                            <div class="panel-body">
                                <h5 class="msg-title">微信缴费使用指南1</h5>
                                <div class="msg-img"><img src="${basePath}/resources/weixin/images/20170831595cf16beb634972a65adb6b14abca9b.jpg" alt=""></div>
                                <p class="msg-text">微信缴费使用指南</p>
                            </div>
                            <div class="mask-bg"><div class="mask-icon"><i class="icon-ok"></i></div></div>
                        </div>
                    </div>
                    <div id="col_2" class="col-xs-4">
                        <div class="panel panel-default">
                            <div class="panel-heading msg-date">
                                08月31日
                            </div>
                            <div class="panel-body">
                                <h5 class="msg-title">微信缴费使用指南2</h5>
                                <div class="msg-img"><img src="${basePath}/resources/weixin/images/1.png" alt=""></div>
                                <p class="msg-text">微信缴费使用指南</p>
                            </div>
                            <div class="mask-bg"><div class="mask-icon"><i class="icon-ok"></i></div></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info ensure">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>--%>
<div id="reminderModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span>×</span></button>
                <h4 class="modal-title">
                    温馨提示
                </h4>
            </div>
            <div class="modal-body">
                <h5>添加子菜单后，一级菜单的内容将被清除。确定添加子菜单？</h5>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info reminder">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<div id="abnormalModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span>×</span></button>
                <h4 class="modal-title">
                    温馨提示
                </h4>
            </div>
            <div class="modal-body">
                <h5>数据异常</h5>
            </div>
            <div class="modal-footer">
                <!-- <button type="button" class="btn btn-info reminder">确定</button> -->
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
</body>

</html>

