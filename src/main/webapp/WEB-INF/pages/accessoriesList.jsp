<%@ include file="/common/taglibs.jsp"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
    <title><fmt:message key="accessoriesList.title"/></title>
    <meta name="menu" content="AccessoriesMenu"/>
</head>

<div class="span10">
    <h2><fmt:message key="accessoriesList.heading"/></h2>

    <form method="post" action="${ctx}/accessoriess" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="accessoriesList.message"/>

    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/editAccessories'/>" >
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
        </a>
        <a class="btn" href="<c:url value="/mainMenu"/>" >
            <i class="icon-ok"></i> <fmt:message key="button.done"/>
        </a>
    </div>

    <display:table name="accessoriess" class="table table-condensed table-striped table-hover" requestURI="" id="accessoriesList" export="true" pagesize="25" defaultsort="1" sort="list" defaultorder="descending">
        <display:column property="id" sortable="true" href="editAccessories" media="html"
            paramId="id" paramProperty="id" titleKey="accessories.id"/>
        <display:column property="id" media="csv excel xml pdf" titleKey="accessories.id"/>
        <display:column property="name" sortable="true" titleKey="accessories.name"/>
		<display:column property="stock" sortable="true" titleKey="accessories.stock"/>
		<display:column property="usedAmount" sortable="true" title="使用数量"/>
		<display:column property="remainderAmount" sortable="true" title="剩余数量"/>
		
        <display:setProperty name="paging.banner.item_name"><fmt:message key="accessoriesList.accessories"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="accessoriesList.accessoriess"/></display:setProperty>

        <display:setProperty name="export.excel.filename"><fmt:message key="accessoriesList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="accessoriesList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="accessoriesList.title"/>.pdf</display:setProperty>
    </display:table>
</div>
