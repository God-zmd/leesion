<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<lesson:page title="manage.company.my.info">
    <!-- breadcrumb -->
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do"><spring:message code="common.homepage"/></a>
            </li>
            <li class="active"><spring:message code="manage.company.my.info"/></li>
        </ul><!-- /.breadcrumb -->
    </div>
    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <h3 class="header smaller lighter blue"><spring:message code="manage.company.my.info"/>

                        </h3>
                        <form id="companyInfo-edit-form" action="manage/company/save_edit.do" method="post"
                              class="form-horizontal" role="form">
                            <input type="hidden" name="id" value="<c:out value="${company.id}"/>"/>

                            <div class="form-group">

                                <label class="col-sm-3 control-label no-padding-right"> <spring:message
                                        code="city"/> </label>

                                <div class="col-sm-3">
                                    <lesson:citySelect fieldName="city"
                                                        selectedCityId="${company.city.id}"/>
                                </div>

                            </div>


                            <div class="form-group">

                                <label class="col-sm-3 control-label no-padding-right"> <spring:message
                                        code="name.zh"/> </label>

                                <div class="col-sm-3">
                                    <input type="text" autocomplete="off" id="primary" name="name.primary" minlength="1" maxlength="200"
                                           class="required form-control"
                                           value="<c:out value="${company.name.primary}"/>"/>
                                </div>

                                <label class="col-md-2 control-label no-padding-right"> <spring:message
                                        code="name.en"/> </label>

                                <div class="col-sm-3">
                                    <input type="text" autocomplete="off" id="secondary" name="name.secondary" maxlength="200"
                                           class="required form-control"
                                           value="<c:out value="${company.name.secondary}"/>"/>

                                </div>
                            </div>

                            <div class="form-group">

                                <label class="col-sm-3 control-label no-padding-right"> <spring:message
                                        code="address"/> </label>

                                <div class="col-sm-3">
                                    <input type="text" autocomplete="off" id="address" name="address" maxlength="200"
                                           class="  form-control"
                                           value="<c:out value="${company.address}"/>"/>

                                </div>

                                <label class="col-md-2 control-label no-padding-right"> <spring:message
                                        code="zipCode"/> </label>

                                <div class="col-sm-3">

                                    <input type="text" autocomplete="off" id="zipCode" name="zipCode" maxlength="20"
                                           class="  form-control"
                                           value="<c:out value="${company.zipCode}"/>"/>

                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"> <spring:message
                                        code="fax"/> </label>

                                <div class="col-sm-3">
                                    <input type="text" autocomplete="off" id="fax" name="fax" maxlength="50"
                                           class=" form-control"
                                           value="<c:out value="${company.fax}"/>"/>

                                </div>

                                <label class="col-md-2 control-label no-padding-right"> <spring:message
                                        code="tel"/> </label>

                                <div class="col-sm-3">
                                    <input type="text" autocomplete="off" id="tel" name="tel" maxlength="50"
                                           class=" form-control"
                                           value="<c:out value="${company.tel}"/>"/>

                                </div>
                            </div>

                            <div class="form-group">

                                <label class="col-sm-3 control-label no-padding-right"> <spring:message code="company.homepage"/> </label>

                                <div class="col-sm-3">
                                    <input type="text" autocomplete="off" id="homepage" name="homepage" maxlength="100"
                                           class=" form-control"
                                           value="<c:out value="${company.homepage}"/>"/>

                                </div>

                                <label class="col-md-2 control-label no-padding-right"> <spring:message
                                        code="email"/> </label>

                                <div class="col-sm-3">
                                    <input type="text" autocomplete="off" id="email" name="email" minlength="1" maxlength="100"
                                           class="email form-control"
                                           value="<c:out value="${company.email}"/>"/>

                                </div>
                            </div>


                            <div class="form-group">

                                <label class="col-sm-3 control-label no-padding-right"> <spring:message
                                        code="bank"/> </label>

                                <div class="col-sm-8">
                                    <input type="text" autocomplete="off" id="bank" name="bank" maxlength="80"
                                           class=" form-control" value="${company.bank}">

                                    </input>

                                </div>
                            </div>
                            <div class="form-group">

                                <label class="col-sm-3 control-label no-padding-right"> <spring:message
                                        code="accountName"/> </label>

                                <div class="col-sm-8">
                                    <input type="text" autocomplete="off" id="accountName" name="accountName" maxlength="80"
                                           class=" form-control" value="${company.accountName}">

                                    </input>

                                </div>
                            </div>
                            <div class="form-group">

                                <label class="col-sm-3 control-label no-padding-right"> <spring:message
                                        code="vatNumber"/> </label>

                                <div class="col-sm-8">
                                    <input type="text" autocomplete="off" id="vatNumber" name="vatNumber" maxlength="80"
                                           class="required form-control" value="${company.vatNumber}">

                                    </input>

                                </div>

                            </div>
                            <div class="form-group">

                                <label class="col-sm-3 control-label no-padding-right"> <spring:message
                                        code="emergency"/> </label>

                                <div class="col-sm-8">
                                    <input type="text" autocomplete="off" id="emergency" name="emergency" maxlength="100"
                                           class="required form-control" value="${company.emergency}">

                                    </input>

                                </div>
                            </div>


                            <div class="clearfix form-actions">
                                <div class="text-center">
                                    <button id="submit-add" class="btn btn-info" type="submit">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        <spring:message code="button.submit"/>
                                    </button>

                                </div>
                            </div>
                        </form>
                    </div><!-- /.span -->
                </div><!-- /.row -->
                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div>
</lesson:page>
