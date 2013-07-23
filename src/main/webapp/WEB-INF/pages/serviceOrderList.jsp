<%@ include file="/common/taglibs.jsp"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
    <title><fmt:message key="serviceOrderList.title"/></title>
    <meta name="menu" content="ServiceOrderMenu"/>
</head>

<div class="span10">
    <h2><fmt:message key="serviceOrderList.heading"/></h2>

    <form method="post" action="${ctx}/serviceOrders" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="serviceOrderList.message"/>

    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/editServiceOrder'/>" >
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
        </a>
        <a class="btn" href="<c:url value="/mainMenu"/>" >
            <i class="icon-ok"></i> <fmt:message key="button.done"/>
        </a>
    </div>

    <display:table name="serviceOrders" class="table table-condensed table-striped table-hover" requestURI="" id="serviceOrderList" export="true" pagesize="25" defaultsort="5" sort="list" defaultorder="descending">
        <display:column property="id" sortable="true" href="editServiceOrder" media="html"
            paramId="id" paramProperty="id" titleKey="serviceOrder.id"/>
        <display:column property="id" media="csv excel xml pdf" titleKey="serviceOrder.id"/>
        <display:column property="description" sortable="true" titleKey="serviceOrder.description"/>
        <display:column property="fault" sortable="true" titleKey="serviceOrder.fault"/>
        <display:column property="price" sortable="true" titleKey="serviceOrder.price"/>
        <display:column property="createTime" sortable="true" titleKey="serviceOrder.createTime" format="{0,date,yyyy-MM-dd hh:mm:ss}"/>
        <display:column property="status.name" sortable="true" titleKey="serviceOrder.status"/>
        <display:column property="customer.name" sortable="true" titleKey="customer.name" />
        <display:column property="customer.phoneNum" sortable="true" titleKey="customer.phoneNum" />
        <display:column property="customer.gender.name" sortable="true" titleKey="customer.gender" />
        <display:setProperty name="paging.banner.item_name"><fmt:message key="serviceOrderList.serviceOrder"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="serviceOrderList.serviceOrders"/></display:setProperty>
        <display:setProperty name="export.excel.filename"><fmt:message key="serviceOrderList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="serviceOrderList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="serviceOrderList.title"/>.pdf</display:setProperty>
    </display:table>
</div>
