<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<lesson:page title="department.title.${cmd}">
     <jsp:attribute name="script">
        <script type="application/javascript">
            jQuery(function ($) {
                $('#allAreas').on('change', function (e) {
                    e.preventDefault();
                    if($(this).val() == 'true'){
                        $(".show-hidden").each(function () {
                            $(this).hide();
                        });
                    }else{
                        $(".show-hidden").each(function () {
                            $(this).show();
                        });
                    }
                });
            });
        </script>
    </jsp:attribute>
    <jsp:body>
    <!-- breadcrumb -->
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do"><spring:message code="common.homepage" /></a>
            </li>
            <li>
                <a href="manage/departments/list.do"><spring:message code="department.title" /></a>
            </li>
            <li class="active">
                <spring:message code="department.title.${cmd}" />
            </li>
        </ul>
    </div>
    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <h3 class="header smaller lighter blue"><spring:message code="department.title.${cmd}" />
                            <sec:authorize ifAnyGranted="OPT_MANAGE_DEPARTMENT_LIST">
                                <span class="btn-group pull-right">
                                    <a href="manage/departments/list.do" class="btn btn-sm btn-primary"><i class="ace-icon fa fa-angle-left"></i><spring:message code="button.back" /></a>
                                </span>
                            </sec:authorize>
                        </h3>
                        <form id="department-form" action="manage/departments/save_${cmd}.do" method="post" class="form-horizontal" role="form">
                            <input type="hidden" name="id" value="<c:out value="${department.id}"/>" />
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="name"><spring:message code="department.name" /></label>

                                <div class="col-sm-6">
                                    <input type="text" autocomplete="off" id="name" name="name" minlength="1" maxlength="50" class="required form-control" value="<c:out value="${department.name}" />"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="allAreas"><spring:message code="department.allAreas" /></label>
                                <div class="col-sm-6">
                                <select id="allAreas" name="allArea" class="chosen-select form-control">
                                    <option value=""></option>
                                    <option value="true" <c:if test="${(not empty department.allArea) && department.allArea}">selected</c:if> ><spring:message code="common.yes"/> </option>
                                    <option value="false" <c:if test="${(not empty department.allArea) && !department.allArea}">selected</c:if> ><spring:message code="common.no"/> </option>
                                </select>
                                </div>
                            </div>

                            <div class="form-group show-hidden">
                                <label class="col-sm-3 control-label no-padding-right" for="countries"><spring:message code="department.includeCountries" /></label>
                                <div class="col-sm-6">
                                    <lesson:countrySelect fieldName="countries" multiple="true" selectedCountryId="" fieldStyleClass="width: 100%;" width="100%;" selectedCounties="${department.countries}"/>
                                </div>
                            </div>
                            <div class="form-group show-hidden">
                                <label class="col-sm-3 control-label no-padding-right" for="includeCities"> <spring:message code="department.includeCities" /> </label>
                                <div class="col-sm-6">
                                    <lesson:multiCitySelect fieldName="includeCities" multiple="true" selectedCities="${department.includeCities}" />
                                </div>
                            </div>
                            <div class="form-group show-hidden">
                                <label class="col-sm-3 control-label no-padding-right" for="excludeCities"> <spring:message code="department.excludeCities" /> </label>
                                <div class="col-sm-6">
                                    <lesson:multiCitySelect fieldName="excludeCities" multiple="true" selectedCities="${department.excludeCities}" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-left" for="groupType"> <spring:message code="department.groupType" /></label>
                                <div class="col-sm-6">
                                    <lesson:tripTypes fieldName="tripTypes" tripTypes="${department.tripTypes}"  width="100%"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-left" for="orderType"> <spring:message code="department.orderType" /></label>
                                <div class="col-sm-6">
                                    <lesson:payableTypes fieldName="payableTypes" payableTypes="${department.payableTypes}" withNone="false" width="100%"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-left" for="members"> <spring:message code="department.members" /></label>
                                <div class="col-sm-6">
                                    <lesson:userCheck fieldName="users" selectedUsers="${department.users}" />
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-left" for="messageType"> <spring:message code="department.messageType" /></label>
                                <div class="col-sm-6">
                                    <lesson:messageTypes fieldName="messageTypes" messageTypes="${department.messageTypes}" withNone="false" width="100%"/>
                                </div>
                            </div>


                            <div class="clearfix form-actions">
                                <div class="text-center">
                                    <sec:authorize ifAnyGranted="OPT_MANAGE_DEPARTMENT_${fn:toUpperCase(cmd)}">
                                        <button id="submit-add" class="btn btn-info" type="submit">
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            <spring:message code="button.save" />
                                        </button>
                                    </sec:authorize>
                                </div>
                            </div>

                        </form>
                    </div><!-- /.span -->
                </div><!-- /.row -->

                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div>
    </jsp:body>
</lesson:page>
