<%@ include file="/common/taglibs.jsp"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
    <title><fmt:message key="employeeDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='employeeDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="employeeList.employee"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="employeeDetail.heading"/></h2>
    <fmt:message key="employeeDetail.message"/>
</div>

<div class="span7">
    <s:form id="employeeForm" action="saveEmployee" method="post" validate="true" cssClass="well form-horizontal">
            <s:hidden key="employee.id"/>
        <s:textfield key="employee.age" required="false" maxlength="255" />
        <s:textfield key="employee.email" required="false" maxlength="255" />
        <s:textfield key="employee.employeeNo" required="false" maxlength="255" />
        <s:textfield key="employee.gender" required="false" maxlength="255" />
        <s:textfield key="employee.nick" required="false" maxlength="255" />
        <s:textfield key="employee.qq" required="false" maxlength="255" />
        <s:textfield key="employee.trueName" required="false" maxlength="255" />

        <div id="actions" class="form-actions">
            <s:submit type="button" cssClass="btn btn-primary" method="save" key="button.save" theme="simple">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </s:submit>
            <c:if test="${not empty employee.id}">
                <s:submit type="button" cssClass="btn btn-warning" method="delete" key="button.delete"
                    onclick="return confirmMessage(msgDelConfirm)" theme="simple">
                    <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
                </s:submit>
            </c:if>
            <s:submit type="button" cssClass="btn" method="cancel" key="button.cancel" theme="simple">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </s:submit>
        </div>
    </s:form>
</div>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['employeeForm']).focus();
    });
</script>
