<%@ include file="/common/taglibs.jsp"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
    <title><fmt:message key="brandList.title"/></title>
    <meta name="menu" content="BrandMenu"/>
</head>

<div class="span10">
    <h2><fmt:message key="brandList.heading"/></h2>

    <form method="post" action="${ctx}/brands" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="brandList.message"/>

    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/editBrand'/>" >
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
        </a>
        <a class="btn" href="<c:url value="/mainMenu"/>" >
            <i class="icon-ok"></i> <fmt:message key="button.done"/>
        </a>
    </div>
    <display:table name="brands" class="table table-condensed table-striped table-hover" requestURI="" id="brandList" export="true" pagesize="15">
        <display:column property="id" sortable="true" href="editBrand" media="html"
            paramId="id" paramProperty="id" titleKey="brand.id"/>
        <display:column property="id" media="csv excel xml pdf" titleKey="brand.id"/>
        <display:column property="name" sortable="true" titleKey="brand.name"/>
        <display:column property="description" sortable="true" titleKey="brand.description"/>
        <display:column property="officialUrl" sortable="true" titleKey="brand.officialUrl"/>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="brandList.brand"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="brandList.brands"/></display:setProperty>

        <display:setProperty name="export.excel.filename"><fmt:message key="brandList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="brandList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="brandList.title"/>.pdf</display:setProperty>
    </display:table>
</div>
