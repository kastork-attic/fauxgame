<%--
  Created by IntelliJ IDEA.
  User: kirk
  Date: 5/28/13
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>${gameInstance?.gameTitle.displayName}</title>
</head>
<body>

<h2>Game Title Information</h2>

<table>
  <tr>
    <td>URI Token - This is the uri path element that identifies the game title</td>
    <td>${gameInstance?.gameTitle.uriToken}</td>
  </tr>
  <tr>
    <td>Display Name - Unless manually changed, this is just the same as the URI Token.</td>
    <td>${gameInstance?.gameTitle.displayName}</td>
  </tr>
  <tr>
    <td>Number Postions - The number of player positions this game title has.</td>
    <td>${gameInstance?.gameTitle.numberPositions}</td>
  </tr>
  <tr>
    <td>Game Version - A version number for this game title.</td>
    <td>${gameInstance?.gameTitle.gameVersion}</td>
  </tr>
  <tr>
    <td>Date Created</td>
    <td>${gameInstance?.gameTitle.dateCreated}</td>
  </tr>
  <tr>
    <td>Date Updtated</td>
    <td>${gameInstance?.gameTitle.lastUpdated}</td>
  </tr>
  <tr>
    <td>Count - The number of game instances in the database for this title/version</td>
    <td>${gameInstance?.gameTitle.gameInstances.size()}</td>
  </tr>
</table>

<h2>Game Instance Information</h2>

<table>
  <tr>
    <td>Liferay Server (EGS)</td>
    <td>${gameInstance?.lobbyServer.baseURL}</td>
  </tr>
  <tr>
    <td>Date Created</td>
    <td>${gameInstance?.dateCreated}</td>
  </tr>
  <tr>
    <td>Date Updtated</td>
    <td>${gameInstance?.lastUpdated}</td>
  </tr>
  <tr>
    <td>Number of positions (roles)</td>
    <td>${gameInstance?.positions.size()}</td>
  </tr>
</table>

<table>
  <tr>
    <th>Role</th>
    <th>Player</th>
    <th>Game State</th>
  </tr>
<g:each in="${gameInstance.positions}" var="p" >
  <tr>
    <td>${p.roleParam}</td>
    <td>${p.playerIdent}</td>
    <td>${p.state}</td>
  </tr>
</g:each>
</table>

<h2>You</h2>

<table>
  <tr>
    <td>Role</td>
    <td>${positionInstance?.roleParam}</td>
  </tr>
  <tr>
    <td>Player</td>
    <td>${positionInstance?.playerIdent}</td>
  </tr>
  <tr>
    <td>Game State</td>
    <td>${positionInstance?.state}</td>
  </tr>
</table>


</body>
</html>