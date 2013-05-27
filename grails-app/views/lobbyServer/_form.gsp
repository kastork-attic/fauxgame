<%@ page import="edu.nps.fauxgame.LobbyServer" %>



<div class="fieldcontain ${hasErrors(bean: lobbyServerInstance, field: 'baseURL', 'error')} ">
	<label for="baseURL">
		<g:message code="lobbyServer.baseURL.label" default="Base URL" />
		
	</label>
	<g:textField name="baseURL" value="${lobbyServerInstance?.baseURL}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lobbyServerInstance, field: 'gameBot', 'error')} ">
	<label for="gameBot">
		<g:message code="lobbyServer.gameBot.label" default="Game Bot" />
		
	</label>
	<g:textField name="gameBot" value="${lobbyServerInstance?.gameBot}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lobbyServerInstance, field: 'lobbyPassword', 'error')} ">
	<label for="lobbyPassword">
		<g:message code="lobbyServer.lobbyPassword.label" default="Lobby Password" />
		
	</label>
	<g:textField name="lobbyPassword" value="${lobbyServerInstance?.lobbyPassword}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lobbyServerInstance, field: 'lobbyUsername', 'error')} ">
	<label for="lobbyUsername">
		<g:message code="lobbyServer.lobbyUsername.label" default="Lobby Username" />
		
	</label>
	<g:textField name="lobbyUsername" value="${lobbyServerInstance?.lobbyUsername}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lobbyServerInstance, field: 'profile', 'error')} ">
	<label for="profile">
		<g:message code="lobbyServer.profile.label" default="Profile" />
		
	</label>
	<g:textField name="profile" value="${lobbyServerInstance?.profile}"/>
</div>

