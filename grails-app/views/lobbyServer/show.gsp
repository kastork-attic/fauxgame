
<%@ page import="edu.nps.fauxgame.LobbyServer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'lobbyServer.label', default: 'LobbyServer')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-lobbyServer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-lobbyServer" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list lobbyServer">
			
				<g:if test="${lobbyServerInstance?.baseURL}">
				<li class="fieldcontain">
					<span id="baseURL-label" class="property-label"><g:message code="lobbyServer.baseURL.label" default="Base URL" /></span>
					
						<span class="property-value" aria-labelledby="baseURL-label"><g:fieldValue bean="${lobbyServerInstance}" field="baseURL"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lobbyServerInstance?.gameBot}">
				<li class="fieldcontain">
					<span id="gameBot-label" class="property-label"><g:message code="lobbyServer.gameBot.label" default="Game Bot" /></span>
					
						<span class="property-value" aria-labelledby="gameBot-label"><g:fieldValue bean="${lobbyServerInstance}" field="gameBot"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lobbyServerInstance?.lobbyPassword}">
				<li class="fieldcontain">
					<span id="lobbyPassword-label" class="property-label"><g:message code="lobbyServer.lobbyPassword.label" default="Lobby Password" /></span>
					
						<span class="property-value" aria-labelledby="lobbyPassword-label"><g:fieldValue bean="${lobbyServerInstance}" field="lobbyPassword"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lobbyServerInstance?.lobbyUsername}">
				<li class="fieldcontain">
					<span id="lobbyUsername-label" class="property-label"><g:message code="lobbyServer.lobbyUsername.label" default="Lobby Username" /></span>
					
						<span class="property-value" aria-labelledby="lobbyUsername-label"><g:fieldValue bean="${lobbyServerInstance}" field="lobbyUsername"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lobbyServerInstance?.profile}">
				<li class="fieldcontain">
					<span id="profile-label" class="property-label"><g:message code="lobbyServer.profile.label" default="Profile" /></span>
					
						<span class="property-value" aria-labelledby="profile-label"><g:fieldValue bean="${lobbyServerInstance}" field="profile"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${lobbyServerInstance?.id}" />
					<g:link class="edit" action="edit" id="${lobbyServerInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
