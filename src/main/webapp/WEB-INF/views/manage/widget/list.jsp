<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<lesson:page title="widget.title.list">

    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do"><spring:message code="common.homepage"/></a>
            </li>

            <li>
                <spring:message code="widget.title"/>
            </li>
            <li class="active"><spring:message code="widget.title.list"/></li>
        </ul><!-- /.breadcrumb -->
    </div>

    <div class="page-content">

		
        <!-- PAGE CONTENT BEGINS -->
        <div class="row">
            <div class="col-xs-12">
                <h3 class="header smaller lighter blue"><spring:message code="widget.title.list"/>
                            <span class="btn-group pull-right">
                                <sec:authorize ifAnyGranted="OPT_MANAGE_WIDGET_ADD">
                                    <a href="manage/widget/add.do" class="btn btn-sm btn-primary">
                                        <i class="ace-icon glyphicon glyphicon-plus"></i>
                                        <span class="hidden-320">
                                            <spring:message code="widget.title.add"/>
                                        </span>
                                    </a>
                                </sec:authorize>
                            </span>
                </h3>
                <table id="simple-table" class="table  table-bordered table-hover">
                    <thead>
                        <tr>

                            <th><spring:message code="widget.name"/></th>
                            <th class="hidden-480"><spring:message code="widget.type" /></th>
                            <th class="hidden-480"><spring:message code="widget.url"/></th>
                            <th class="hidden-480"><spring:message code="widget.description" /></th>
                            <th class="center"></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${widgets}" var="widget">
                        <tr id="tr-${widget.id}">

                            <td>
                                <strong style="color: #FF6600;">${widget.name}</strong>
                            </td>
                            <td class="hidden-480"><spring:message code="widget.type.${widget.type.value}"/> </td>
                            <td class="hidden-480">${widget.url}</td>

                            <td class="hidden-480">${widget.description}</td>
                            <td>
                                <div class="hidden-sm hidden-xs btn-group action-buttons">
                                    <sec:authorize ifAnyGranted="OPT_MANAGE_WIDGET_EDIT">
                                        <a href="manage/widget/edit.do?id=${widget.id}"
                                           class="green">
                                            <i class="ace-icon fa fa-pencil-square-o bigger-130"></i>
                                        </a>
                                    </sec:authorize>
                                    <span class="vbar"></span>
                                    <sec:authorize ifAnyGranted="OPT_MANAGE_WIDGET_DELETE">
                                        <a class="red btn-delete-modal" style="cursor: pointer"
                                           data-url="manage/widget/delete.do" data-id="${widget.id}">
                                            <i class="ace-icon fa fa-trash-o bigger-130"></i>
                                        </a>
                                    </sec:authorize>
                                </div>
                                <div class="hidden-md hidden-lg">
                                    <div class="inline pos-rel">
                                        <button class="btn btn-minier btn-primary dropdown-toggle"
                                                data-toggle="dropdown" data-position="auto">
                                            <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                        </button>

                                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                            <li>
                                                <a class="green bigger-140 show-details-btn">
                                                    <i class="ace-icon fa fa-angle-double-down"></i>
                                                </a>
                                            </li>
                                            <sec:authorize ifAnyGranted="OPT_MANAGE_WIDGET_EDIT">
                                                <li>
                                                    <a href="manage/widget/edit.do?id=${widget.id}">
																<span class="green">
																	<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																</span>
                                                    </a>
                                                </li>
                                            </sec:authorize>
                                            <sec:authorize ifAnyGranted="OPT_MANAGE_WIDGET_DELETE">
                                                <li>
                                                    <a class="btn-delete-modal" data-url="manage/widget/delete.do"
                                                       data-id="${widget.id}">
																<span class="red">
																	<i class="ace-icon fa fa-trash-o bigger-120"></i>
																</span>
                                                    </a>
                                                </li>
                                            </sec:authorize>
                                        </ul>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr class="detail-row" id="tr-detail-${widget.id}">
                            <td colspan="8" id="td-detail-${widget.id}">
                                <div class="table-detail">
                                    <div class="profile-user-info profile-user-info-striped">
                                        <div class="profile-info-row">
                                            <div class="profile-info-name"><spring:message code="widget.type"/></div>
                                            <div class="profile-info-value">
                                                <span><spring:message code="widget.type.${widget.type.value}"/></span>
                                            </div>
                                        </div>

                                        <div class="profile-info-row">
                                            <div class="profile-info-name"><spring:message code="widget.url"/></div>
                                            <div class="profile-info-value">
                                                <span><c:out value="${widget.url}"/></span>
                                            </div>
                                        </div>

                                        <div class="profile-info-row">
                                            <div class="profile-info-name"><spring:message code="widget.description"/></div>
                                            <div class="profile-info-value">
                                                <span><c:out value="${widget.description}"/></span>
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
    </div>
    <!-- /.col -->

</lesson:page>
