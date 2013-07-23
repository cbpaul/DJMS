<%@ include file="/common/taglibs.jsp"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
    <title><fmt:message key="customerList.title"/></title>
    <meta name="menu" content="CustomerMenu"/>
</head>

<div class="span10">
    <h2><fmt:message key="customerList.heading"/></h2>

    <form method="post" action="${ctx}/customers" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="customerList.message"/>

    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/editCustomer'/>" >
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
        </a>
        <a class="btn" href="<c:url value="/mainMenu"/>" >
            <i class="icon-ok"></i> <fmt:message key="button.done"/>
        </a>
    </div>

    <display:table name="customers" class="table table-condensed table-striped table-hover" requestURI="" id="customerList" export="true" pagesize="25">
        <display:column property="id" sortable="true" href="editCustomer" media="html"
            paramId="id" paramProperty="id" titleKey="customer.id"/>
        <display:column property="id" media="csv excel xml pdf" titleKey="customer.id"/>
        <display:column property="gender.name" sortable="true" titleKey="customer.gender"/>
        <display:column property="name" sortable="true" titleKey="customer.name"/>
        <display:column property="phoneNum" sortable="true" titleKey="customer.phoneNum"/>
        <display:column property="type.name" sortable="true" titleKey="customer.type"/>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="customerList.customer"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="customerList.customers"/></display:setProperty>

        <display:setProperty name="export.excel.filename"><fmt:message key="customerList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="customerList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="customerList.title"/>.pdf</display:setProperty>
    </display:table>
</div>
