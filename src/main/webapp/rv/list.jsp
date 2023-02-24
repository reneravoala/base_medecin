<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rendez-vous</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
<link href="https://unpkg.com/@tailwindcss/forms@0.2.1/dist/forms.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="../header.jsp" %>
<div class="my-5 mx-auto px-4 sm:px-6 lg:px-8 w-3/4">
    <div class="sm:flex sm:items-center">
        <div class="sm:flex-auto">
            <h1 class="text-xl font-semibold text-gray-900">Rendez-vous  <c:if test='${client != "" }'> (${client})</c:if></h1>
            <p class="mt-2 text-sm text-gray-700">Liste des rendez-vous</p>
        </div>
        <div class="mt-4 sm:mt-0 sm:ml-16 sm:flex-none">
            <a href="${pageContext.request.contextPath}/rv/add" type="button" class="inline-flex items-center justify-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 sm:w-auto">Ajouter</a>
        </div>
    </div>
    <div class="mt-8 flex flex-col">
        <div class="-my-2 -mx-4 overflow-x-auto sm:-mx-6 lg:-mx-8">
            <div class="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
                <div class="overflow-hidden shadow ring-1 ring-black ring-opacity-5 md:rounded-lg">
                    <table class="min-w-full divide-y divide-gray-300">
                        <thead class="bg-gray-50">
                        <tr class="divide-x divide-gray-200">
                            <th scope="col" class="py-3.5 pl-4 pr-4 text-left text-sm font-semibold text-gray-900 sm:pl-6">Client</th>
                            <th scope="col" class="px-4 py-3.5 text-left text-sm font-semibold text-gray-900">Date</th>
                            <th scope="col" class="px-4 py-3.5 text-left text-sm font-semibold text-gray-900">Créneau</th>
                            <th scope="col" class="relative py-3.5 pl-3 pr-4 sm:pr-6">
                                <span class="sr-only">Modifier</span>
                            </th>
                        </tr>
                        </thead>
                        <tbody class="divide-y divide-gray-200 bg-white">
                        <c:forEach items="${rvs}" var="rv">
                        <tr class="divide-x divide-gray-200">
                            <td class="whitespace-nowrap py-4 pl-4 pr-4 text-sm font-medium text-gray-900 sm:pl-6">${rv.getClient().getNom()} ${rv.getClient().getPrenom()}</td>
                            <td class="whitespace-nowrap p-4 text-sm text-gray-500">${rv.getJourString()}</td>
                            <td class="whitespace-nowrap p-4 text-sm text-gray-500">${rv.getCreneau().getHeureDebut()} - ${rv.getCreneau().getHeureFin()} (${rv.getCreneau().getMedecin().getNomComplet()})</td>
                            <td class="flex gap-4 relative whitespace-nowrap py-4 pl-3 pr-4 text-right text-sm font-medium sm:pr-6">
                                <a href="${pageContext.request.contextPath}/rv/edit?id=${rv.id}" class="p-2 rounded text-indigo-600 hover:bg-indigo-100">Modifier</a>
                                <form action="" method="post" onsubmit="return confirm('Etes-vous sûr de vouloir annuler ce rendez-vous ?');">
                                    <input type="hidden" name="id" value="${rv.id}">
                                    <button type="submit" class="p-2 rounded text-red-600 hover:bg-red-100">Annulez</button>
                                </form>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>