<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>
<#assign dateExists = false>
<%@ include file="/common/taglibs.jsp"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
    <title><fmt:message key="${pojoNameLower}Detail.title"/></title>
    <meta name="heading" content="<fmt:message key='${pojoNameLower}Detail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="${pojoNameLower}List.${pojoNameLower}"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value=${'"'}${r"${delObject}"}${'"'}/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="${pojoNameLower}Detail.heading"/></h2>
    <fmt:message key="${pojoNameLower}Detail.message"/>
</div>

<div class="span7">
    <s:form id="${pojoNameLower}Form" action="save${pojo.shortName}" method="post" validate="true" cssClass="well form-horizontal">
<#rt/>
<#foreach field in pojo.getAllPropertiesIterator()>
<#if field.equals(pojo.identifierProperty)>
    <#assign idFieldName = field.name>
    <#if field.value.identifierGeneratorStrategy == "assigned">
        <#lt/>        <s:textfield key="${pojoNameLower}.${field.name}" required="true"/>
    <#else>
            <s:hidden key="${pojoNameLower}.${field.name}"/>
    </#if>
<#elseif !c2h.isCollection(field) && !c2h.isManyToOne(field) && !c2j.isComponent(field)>
    <#foreach column in field.getColumnIterator()>
        <#if field.value.typeName == "java.util.Date" || field.value.typeName == "date">
            <#assign dateExists = true>
            <#lt/>        <s:textfield key="${pojoNameLower}.${field.name}" required="${(!column.nullable)?string}" <#if (column.length > 0)>maxlength="${column.length?c}" </#if>size="11" title="date" datepicker="true"/>
        <#elseif field.value.typeName == "boolean" || field.value.typeName == "java.lang.Boolean">
            <#lt/>        <s:checkbox key="${pojoNameLower}.${field.name}" theme="css_xhtml"/>
        <#else>
            <#lt/>        <s:textfield key="${pojoNameLower}.${field.name}" required="${(!column.nullable)?string}" <#if (column.length > 0)>maxlength="${column.length?c}" </#if>/>
        </#if>
    </#foreach>
<#elseif c2h.isManyToOne(field)>
    <#foreach column in field.getColumnIterator()>
            <#lt/>        <!-- todo: change this to read the identifier field from the other pojo -->
            <#lt/>        <s:select name="${pojoNameLower}.${field.name}.id" list="${field.name}List" listKey="id" listValue="id"></s:select>
    </#foreach>
</#if>
</#foreach>

        <div id="actions" class="form-actions">
            <s:submit type="button" cssClass="btn btn-primary" method="save" key="button.save" theme="simple">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </s:submit>
            <c:if test="${'$'}{not empty ${pojoNameLower}.${idFieldName}}">
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

<#if dateExists><#rt/>
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${'$'}{pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${r"${pageContext.request.locale.language}"}.js'/>"></script>
</c:if>
</#if><#rt/>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['${pojoNameLower}Form']).focus();
<#if dateExists>
        ${'$'}('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${r"${pageContext.request.locale.language}"}'});
</#if>
    });
</script>