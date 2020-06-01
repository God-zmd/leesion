<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<lesson:page title="sMSBox.title.list">
    <jsp:attribute name="css">
        <style type="text/css">

        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
            jQuery(function($) {
                var mailTables = $('.mail-table');
                var myTable = mailTables.DataTable({});

                myTable.on( 'select', function ( e, dt, type, index ) {
                    if ( type === 'row' ) {
                        $( myTable.row( index ).node() ).find('input:checkbox').prop('checked', true);
                    }
                } );
                myTable.on( 'deselect', function ( e, dt, type, index ) {
                    if ( type === 'row' ) {
                        $( myTable.row( index ).node() ).find('input:checkbox').prop('checked', false);
                    }
                } );

                /////////////////////////////////
                //table checkboxes
                $('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);

                mailTables.on('click', 'input[type=checkbox].checkAll', function(){
                    if ($(this).prop("checked") == true) {
                        $(this).parents('.mail-table').find('th input[type=checkbox]').prop('checked', true);
                    }else {
                        $(this).parents('.mail-table').find('th input[type=checkbox]').prop('checked', false);
                    }
                });



                $('.delete-selected-btn').on('click', function() {
                    var ids = [];
                    $(this).parents('.mail-list-container').find('table > tbody > tr > th input[type=checkbox]').each(function(){
                        var data_id = $(this).attr('data-id');
                        if (data_id) {
                            ids.push(data_id);
                        }
                    });
                    if (ids.length > 0) {
                        var data_url = $(this).attr("data-url");
                        bootbox.dialog({
                            message: "<span class='bigger-110'>" + lesson_message.prompt_msg + "</span>", buttons: {
                                "cancel": {
                                    "label": "<i class='ace-icon fa fa-remove'></i> " + lesson_message.prompt_cancel,
                                    "className": "btn-sm btn-gray",
                                }, "Confirm": {
                                    "label": "<i class='ace-icon fa fa-check'></i> " + lesson_message.prompt_confirm,
                                    "className": "btn-sm btn-primary",
                                    "callback": function () {
                                        $.post(data_url, {
                                            "ids": ids.join(',')
                                        }, function (result) {
                                            if (result) {
                                                for (var i = 0; i < ids.length; i++) {
                                                    var data_id = ids[i];
                                                    $("#tr-" + data_id).remove();
                                                    $("#tr-detail-" + data_id).remove();
                                                }

                                            }
                                        });

                                    }
                                }
                            }
                        });
                    }
                });

            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do">
                        <spring:message code="common.homepage"/>
                    </a>
                </li>
                <li class="active">
                    <spring:message code="sMSBox.title.list"/>
                </li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12 mail-list-container">
                            <h3 class="header smaller lighter blue">
                                <spring:message code="sMSMessage.waitForSending.list"/>
                                <span class="btn-group pull-right">
                                     <a href="javascript:;" class="delete-selected-btn btn btn-sm btn-danger"
                                        data-url="manage/mail/batchDelete.do" data-id="">
                                        <i class="ace-icon fa fa-trash-o"></i>
                                        <spring:message code="sMSMessage.action.deleteSelected"/>
                                    </a>
                                <a class="btn btn-sm btn-primary" href="email/add.do" target="_blank">
                                    <i class="ace-icon glyphicon glyphicon-plus"></i>
                                    <spring:message code="sMSMessage.title.add"/>
                                </a>
                            </span>
                            </h3>
                            <table id="waiting-table" class="table mail-table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="center">
                                        <label class="pos-rel">
                                            <input type="checkbox" class="ace checkAll" />
                                            <span class="lbl"></span>
                                        </label>
                                    </th>
                                    <th><spring:message code="sMSMessage.receivers"/></th>
                                    <th class="hidden-480"><spring:message code="sMSMessage.title1"/></th>
                                    <th class="hidden-480"><spring:message code="sMSMessage.createTime"/></th>
                                    <th><spring:message code="sMSMessage.sendTimes"/></th>
                                    <th><spring:message code="sMSMessage.lastSendTime"/></th>
                                    <th class="hidden-480"><spring:message code="sMSMessage.lastSendFailReason"/></th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${mails}" var="mail">
                                    <tr id="tr-${mail.id}">
                                        <th class="center">
                                            <label class="pos-rel">
                                                <input type="checkbox" class="ace" data-id="${mail.id}" />
                                                <span class="lbl"></span>
                                            </label>
                                        </th>
                                        <td><c:out value="${mail.toAddr}"/></td>
                                        <td class="hidden-480"><c:out value="${mail.subject}"/></td>
                                        <td class="hidden-480"><c:out value="${mail.createTime}"/></td>
                                        <td><c:out value="${mail.times}"/></td>
                                        <td><c:out value="${mail.sendTime}"/></td>
                                        <td class="hidden-480"><c:out value="${mail.lastErrorMsg}"/></td>
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
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12 mail-list-container">
                            <h3 class="header smaller lighter blue">
                                <spring:message code="sMSMessage.sendFailed.list"/>
                                <span class="btn-group pull-right">
                                    <a href="javascript:;" class="delete-selected-btn btn btn-sm btn-danger"
                                       data-url="manage/mail/batchDelete.do" data-id="">
                                        <i class="ace-icon fa fa-trash-o"></i>
                                        <spring:message code="sMSMessage.action.deleteSelected"/>
                                    </a>
                                    <a class="btn btn-sm btn-primary" href="manage/mail/resetAll.do">
                                        <i class="ace-icon fa fa-share"></i>
                                        <spring:message code="sMSMessage.action.resend"/>
                                    </a>
                                </span>
                            </h3>
                            <table id="failed-table" class="table mail-table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="center">
                                        <label class="pos-rel">
                                            <input type="checkbox" class="ace checkAll" />
                                            <span class="lbl"></span>
                                        </label>
                                    </th>
                                    <th><spring:message code="sMSMessage.receivers"/></th>
                                    <th class="hidden-480"><spring:message code="sMSMessage.title1"/></th>
                                    <th class="hidden-480"><spring:message code="sMSMessage.createTime"/></th>
                                    <th><spring:message code="sMSMessage.sendTimes"/></th>
                                    <th><spring:message code="sMSMessage.lastSendTime"/></th>
                                    <th class="hidden-480"><spring:message code="sMSMessage.lastSendFailReason"/></th>
                                    <th><spring:message code="sMSMessage.operation"/></th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${failedMails}" var="mail">
                                    <tr id="tr-${mail.id}">
                                        <th class="center">
                                            <label class="pos-rel">
                                                <input type="checkbox" class="ace" data-id="${mail.id}" />
                                                <span class="lbl"></span>
                                            </label>
                                        </th>
                                        <td><c:out value="${mail.toAddr}"/></td>
                                        <td class="hidden-480"><c:out value="${mail.subject}"/></td>
                                        <td class="hidden-480"><c:out value="${mail.createTime}"/></td>
                                        <td><c:out value="${mail.times}"/></td>
                                        <td><c:out value="${mail.sendTime}"/></td>
                                        <td class="hidden-480"><c:out value="${mail.lastErrorMsg}"/></td>
                                        <td class="center">
                                            <div class="hidden-sm hidden-xs btn-group action-buttons">
                                                <sec:authorize ifAnyGranted="OPT_MAIL_SEND">
                                                    <a class="green"
                                                       href="manage/mail/reset.do?id=${mail.id}">
                                                        <i class="ace-icon fa fa-refresh bigger-120"></i>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize ifAnyGranted="OPT_MAIL_EDIT">
                                                    <a class="green" href="/email/add.do?id=${mail.id}">
                                                        <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize ifAnyGranted="OPT_MAIL_DELETE">
                                                    <a class="red btn-delete-modal"
                                                       data-url="manage/mail/delete.do" data-id="${mail.id}">
                                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                    </a>
                                                </sec:authorize>
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
    </jsp:body>
</lesson:page>
