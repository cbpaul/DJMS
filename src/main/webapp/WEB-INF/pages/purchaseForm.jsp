<%@ include file="/common/taglibs.jsp"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
    <title><fmt:message key="purchaseDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='purchaseDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="purchaseList.purchase"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="purchaseDetail.heading"/></h2>
    <fmt:message key="purchaseDetail.message"/>
</div>

<div class="span7">
    <s:form id="purchaseForm" action="savePurchase" method="post" validate="true" cssClass="well form-horizontal">
            <s:hidden key="purchase.id"/>
        <s:select name="purchase.accessories.id" list="accessorieList" listKey="id" listValue="name" key="select.accessories"></s:select>
        <s:textfield key="purchase.count" required="false" maxlength="255" />
        <s:textfield key="purchase.price" required="false" maxlength="255" />
		<s:if test="purchase.createTime!=null"><s:hidden key="purchase.createTime"/></s:if>
        <div id="actions" class="form-actions">
            <s:submit type="button" cssClass="btn btn-primary" method="save" key="button.save" theme="simple">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </s:submit>
            <c:if test="${not empty purchase.id}">
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
        $("input[type='text']:visible:enabled:first", document.forms['purchaseForm']).focus();
    });
</script>
