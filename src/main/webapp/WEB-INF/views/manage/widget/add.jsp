<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<lesson:page title="widget.title.${cmd}">
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do"><spring:message code="common.homepage"/></a>
            </li>
            <li>
                <a href="manage/widget/list.do"><spring:message code="widget.title.list"/></a>
            </li>
            <li class="active"><spring:message code="widget.title.${cmd}"/></li>
        </ul><!-- /.breadcrumb -->
    </div>

    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <h3 class="header smaller lighter blue"><spring:message code="widget.title.${cmd}"/>
                            <span class="btn-group pull-right">
                                <a href="javascript:history.go(-1)" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i><spring:message code="button.back"/> </a>
                            </span>
                        </h3>
                        <form id="cityTax-form" action="manage/widget/save_${cmd}.do" method="post"
                              class="form-horizontal" role="form">
                            <input type="hidden" name="cmd" id="cmd" value="${cmd}"/>
                            <input type="hidden" name="id" id="id" value="${widget.id}"/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"><spring:message code="widget.name"/> </label>

                                <div class="col-sm-3">
                                    <input type="text" autocomplete="off" name="name" value="${widget.name}" class="required col-xs-12 col-sm-12">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"> <spring:message
                                        code="widget.type"/> </label>
                                <div class="col-sm-3">
                                    <select name="type" class="required chosen-select col-xs-12 col-sm-12">
                                        <option value="1" ${widget.type.value eq 1 ?'selected':''}><spring:message code="widget.type.1"/></option>

                                        <option value="2" ${widget.type.value eq 2 ?'selected':''}><spring:message code="widget.type.2"/></option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"><spring:message code="widget.url"/> </label>

                                <div class="col-sm-3">
                                    <textarea type="text" autocomplete="off" name="url" value="" class=" col-xs-12 col-sm-12">${widget.url}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"><spring:message code="widget.description"/> </label>

                                <div class="col-sm-3">
                                    <textarea type="text" autocomplete="off" name="description" value="" class=" col-xs-12 col-sm-12">${widget.description}</textarea>
                                </div>
                            </div>



                            <sec:authorize ifAnyGranted="OPT_MANAGE_WIDGET_ADD,OPT__MANAGE_WIDGET_EDIT">

                            <div class="clearfix form-actions">
                                <div class="text-center">

                                    <button class="btn btn-info" type="submit">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        <spring:message code="button.submit"/>
                                    </button>
                                </div>
                            </div>
                            </sec:authorize>

                        </form>
                    </div><!-- /.span -->
                </div><!-- /.row -->

                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div>
</lesson:page>