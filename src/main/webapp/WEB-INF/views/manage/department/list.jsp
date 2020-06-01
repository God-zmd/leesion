<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<lesson:page title="department.title">
    <!-- /.breadcrumb -->
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do"><spring:message code="common.homepage"/></a>
            </li>
            <li>
                <spring:message code="department.title"/>
            </li>
            <li class="active">
                <spring:message code="department.title.list"/>
            </li>
        </ul>
    </div>

    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <h3 class="header smaller lighter blue"><spring:message
                                code="department.title.list"/>
                            <span class="btn-group pull-right">
							    <sec:authorize ifAnyGranted="OPT_MANAGE_DEPARTMENT_ADD">
                                    <a href="manage/departments/add.do"
                                       class="btn btn-sm btn-primary"><i class="ace-icon glyphicon glyphicon-plus"></i><spring:message code="button.add"/></a>
                                </sec:authorize>
                            </span>
                        </h3>
                        <table id="simple-table" class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th><spring:message code="department.name"/></th>
                                <th class="hidden-480"><spring:message code="department.allAreas" /></th>
                                <th class="hidden-480"><spring:message code="department.includeCountries"/></th>
                                <th class="hidden-480"><spring:message code="department.includeCities"/></th>
                                <th class="hidden-480"><spring:message code="department.excludeCities"/></th>
                                <th class="hidden-480"><spring:message code="department.groupType"/></th>
                                <th class="hidden-480"><spring:message code="department.orderType"/></th>
                                <th><spring:message code="department.members"/></th>
                                <th class="center"></th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${departments}" var="department">
                                <tr id="tr-${department.id}">
                                    <td><c:out value="${department.name}"/></td>
                                    <td class="hidden-480">
                                        <c:choose>
                                            <c:when test="${department.allArea}"><spring:message code="common.yes" /></c:when>
                                            <c:otherwise><spring:message code="common.no" /></c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="hidden-480">
                                        <c:forEach var="country" items="${department.countries}">
                                            <lessonTag:i18nNameTag
                                                    i18nName="${country.name}"/> <br/>
                                        </c:forEach>
                                    </td>
                                    <td class="hidden-480">
                                        <c:forEach var="city" items="${department.includeCities}">
                                            <lessonTag:i18nNameTag
                                                    i18nName="${city.name}"/> <br/>
                                        </c:forEach>
                                    </td>
                                    <td class="hidden-480">
                                        <c:forEach var="city" items="${department.excludeCities}">
                                            <lessonTag:i18nNameTag
                                                    i18nName="${city.name}"/> <br/>
                                        </c:forEach>
                                    </td>
                                    <td class="hidden-480">
                                        <c:forEach var="tripType" items="${department.tripTypeList}">
                                        	<spring:message code="department.groupType.${fn:toLowerCase(tripType)}"/><br>
										</c:forEach>
                                    </td>
                                    <td class="hidden-480">
                                        <c:out value="${department.payableTypes}"/>
                                    </td>
                                    <td>
                                        <c:forEach var="member" items="${department.users}">
                                            <c:out value="${member.name}"/> <br/>
                                        </c:forEach>
                                    </td>
                                    <td class="center">
                                        <div class="hidden-sm hidden-xs btn-group action-buttons">
                                            <sec:authorize ifAnyGranted="OPT_MANAGE_DEPARTMENT_EDIT">
                                                <a href="manage/departments/edit.do?id=${department.id}"
                                                   class="green">
                                                    <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                </a>
                                            </sec:authorize>
                                            <sec:authorize ifAnyGranted="OPT_MANAGE_DEPARTMENT_DELETE">
                                                <a class="red btn-delete-modal" data-url="manage/departments/delete.do" data-id="${department.id}">
                                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                </a>
                                            </sec:authorize>
                                        </div>

                                        <div class="hidden-md hidden-lg">
                                            <div class="inline pos-rel">
                                                <button class="blue2 dropdown-toggle" data-toggle="dropdown" data-position="auto">
                                                    <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                                </button>

                                                <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                                    <li>
                                                        <a class="green bigger-140 show-details-btn">
                                                            <i class="ace-icon fa fa-angle-double-down"></i>
                                                            
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <sec:authorize ifAnyGranted="OPT_MANAGE_DEPARTMENT_EDIT">
                                                            <a href="manage/departments/edit.do?id=${department.id}">
                                                                <span class="green">
                                                                    <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                                </span>
                                                            </a>
                                                        </sec:authorize>
                                                    </li>
                                                    <li>
                                                        <sec:authorize ifAnyGranted="OPT_MANAGE_DEPARTMENT_DELETE">
                                                            <a class="btn-delete-modal" data-url="manage/departments/delete.do" data-id="${department.id}">
                                                                <span class="red">
                                                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                                </span>
                                                            </a>
                                                        </sec:authorize>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr class="detail-row" id="tr-detail-${department.id}">
                                    <td colspan="7" id="td-detail-${department.id}">
                                        <div class="table-detail">
                                            <div class="profile-user-info profile-user-info-striped">
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"><spring:message code="department.name"/></div>
                                                    <div class="profile-info-value"><c:out value="${department.name}"/></div>
                                                </div>
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"><spring:message code="department.allAreas"/></div>
                                                    <div class="profile-info-value">
                                                        <c:choose>
                                                            <c:when test="${department.allArea}"><spring:message code="common.yes" /></c:when>
                                                            <c:otherwise><spring:message code="common.no" /></c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </div>
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"><spring:message code="department.includeCountries"/></div>
                                                    <div class="profile-info-value">
                                                        <c:forEach var="country" items="${department.countries}">
                                                            <lessonTag:i18nNameTag
                                                                    i18nName="${country.name}"/> <br/>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"><spring:message code="department.includeCities"/></div>
                                                    <div class="profile-info-value">
                                                        <c:forEach var="city" items="${department.includeCities}">
                                                            <lessonTag:i18nNameTag
                                                                    i18nName="${city.name}"/> <br/>
                                                        </c:forEach>
                                                    </div>
                                                </div>

                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"><spring:message code="department.excludeCities"/></div>
                                                    <div class="profile-info-value">
                                                        <c:forEach var="city" items="${department.excludeCities}">
                                                            <lessonTag:i18nNameTag
                                                                    i18nName="${city.name}"/> <br/>
                                                        </c:forEach>
                                                    </div>
                                                </div>

                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"><spring:message code="department.groupType"/></div>
                                                    <div class="profile-info-value">
                                                     <c:forEach var="tripType" items="${department.tripTypeList}">
			                                        	<spring:message code="department.groupType.${fn:toLowerCase(tripType)}"/><br>
													</c:forEach>
                                                    </div>
                                                </div>

                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"><spring:message code="department.orderType"/></div>
                                                    <div class="profile-info-value">
                                                        <c:out value="${department.payableTypes}"/>                                                    </div>
                                                </div>

                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"><spring:message code="department.orderType"/></div>
                                                    <div class="profile-info-value">
                                                        <c:forEach var="member" items="${department.users}">
                                                            <c:out value="${member.name}"/> <br/>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div><!-- /.span -->
                </div><!-- /.row -->

                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div>
</lesson:page>
