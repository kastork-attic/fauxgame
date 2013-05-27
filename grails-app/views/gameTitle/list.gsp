
<%@ page import="edu.nps.fauxgame.GameTitle" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gameTitle.label', default: 'GameTitle')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-gameTitle" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-gameTitle" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="uriToken" title="${message(code: 'gameTitle.uriToken.label', default: 'Uri Token')}" />
					
						<g:sortableColumn property="displayName" title="${message(code: 'gameTitle.displayName.label', default: 'Display Name')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'gameTitle.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="gameVersion" title="${message(code: 'gameTitle.gameVersion.label', default: 'Game Version')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'gameTitle.lastUpdated.label', default: 'Last Updated')}" />
					
						<g:sortableColumn property="numberPositions" title="${message(code: 'gameTitle.numberPositions.label', default: 'Number Positions')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${gameTitleInstanceList}" status="i" var="gameTitleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${gameTitleInstance.id}">${fieldValue(bean: gameTitleInstance, field: "uriToken")}</g:link></td>
					
						<td>${fieldValue(bean: gameTitleInstance, field: "displayName")}</td>
					
						<td><g:formatDate date="${gameTitleInstance.dateCreated}" /></td>
					
						<td>${fieldValue(bean: gameTitleInstance, field: "gameVersion")}</td>
					
						<td><g:formatDate date="${gameTitleInstance.lastUpdated}" /></td>
					
						<td>${fieldValue(bean: gameTitleInstance, field: "numberPositions")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${gameTitleInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
