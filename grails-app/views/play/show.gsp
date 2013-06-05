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
    <td>Game Title Id</td>
    <td>${gameInstance?.gameTitle.id}</td>
  </tr>
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
    <td>Game Instance Id</td>
    <td>${gameInstance?.id}</td>
  </tr>
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
    <th>Position Id</th>
    <th>Role</th>
    <th>Player</th>
    <th>Game State</th>
  </tr>
<g:each in="${gameInstance.positions}" var="p" >
  <tr>
    <td>${p.id}</td>
    <td>${p.roleParam}</td>
    <td>${p.playerIdent}</td>
    <td>${p.state}</td>
  </tr>
</g:each>
</table>

<h2>What EGS thinks of you</h2>
<p>Results of profile query back to EGS</p>
<table>
  <tr>
    <td>Your true self</td>
    <td>${egsProfile?.casId}</td>
  </tr>
  <tr>
    <td>Player</td>
    <td>${egsProfile?.gamingId}</td>
  </tr>
  <tr>
    <td>The game instance we're talking about</td>
    <td>${egsProfile?.gameInstanceId}</td>
  </tr>
</table>

<h2>What I think of you</h2>
<p>What I've told EGS about you.</p>

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

<h2>Game Actions</h2>
<p>Since I'm not a game, all I let you do is change the state of your player/game
combination and send an update to EGS.  This is used to test out the gamebot update mechanisms.</p>
<ul>
  <li><g:link action="toInit" id="${positionInstance.id}">Change to INIT state</g:link></li>
  <li><g:link action="toPend" id="${positionInstance.id}">Change to PEND state</g:link></li>
  <li><g:link action="toAttn" id="${positionInstance.id}">Change to ATTN state</g:link></li>
  <li><g:link action="toOver" id="${positionInstance.id}">Change to OVER state</g:link></li>
</ul>

</body>
</html>