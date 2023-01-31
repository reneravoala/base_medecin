<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un rendez-vous</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
<link href="https://unpkg.com/@tailwindcss/forms@0.2.1/dist/forms.min.css" rel="stylesheet">
</head>
<body>
<div class="my-5 mx-auto px-4 sm:px-6 lg:px-8 w-2/3">
    <div class="sm:flex sm:items-center">
        <form class="space-y-8 divide-y divide-gray-200" action="${pageContext.request.contextPath}/client/add" method="post">
            <div class="space-y-8 divide-y divide-gray-200 sm:space-y-5">
                <div class="space-y-6 sm:space-y-5">
                    <div>
                        <h3 class="text-lg font-medium leading-6 text-gray-900">Ajouter un rendez-vous</h3>
                    </div>

                    <div class="space-y-6 sm:space-y-5">
                        <div class="sm:grid sm:grid-cols-3 sm:items-start sm:gap-4 sm:border-t sm:border-gray-200 sm:pt-5">
                            <label for="client_id" class="block text-sm font-medium text-gray-700 p-2">Client</label>
                            <div class="mt-1">
                                <select name="client_id" id="client_id" class="mt-1 block w-full rounded-md border-gray-300 py-2 pl-3 pr-10 text-base focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 sm:text-sm">
                                    <c:forEach items="${clients}" var="client">
                                        <option value="${client.getId()}">${client.getPrenom()} ${client.getNom()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
<%--                        <div class="sm:grid sm:grid-cols-3 sm:items-start sm:gap-4 sm:border-t sm:border-gray-200 sm:pt-5">--%>
<%--                            <label for="creneau_id" class="block text-sm font-medium text-gray-700 p-2">Créneaux</label>--%>
<%--                            <div class="mt-1">--%>
<%--                                <select name="creneau_id" id="creneau_id" class="mt-1 block w-full rounded-md border-gray-300 py-2 pl-3 pr-10 text-base focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 sm:text-sm">--%>
<%--                                    <c:forEach items="${creneaux}" var="creneau">--%>
<%--                                        <option value="${creneau.getId()}">${creneau.getHeureDebut()} - ${creneau.getHeureFin()} (${creneau.getMedecin().getNomComplet()})</option>--%>
<%--                                    </c:forEach>--%>
<%--                                </select>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                        <div class="sm:grid sm:grid-cols-3 sm:items-start sm:gap-4 sm:border-t sm:border-gray-200 sm:pt-5">
                            <label for="jour" class="block text-sm font-medium text-gray-700 p-2">Jour du rendez-vous</label>
                            <div class="mt-1">
                                <input type="date" name="jour" id="jour" class="p-2 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" placeholder="Jour">
                            </div>
                        </div>
                    </div>

                    <div class="pt-5">
                        <div class="flex justify-end">
                            <a href="${pageContext.request.contextPath}/rv/index" type="button" class="rounded-md border border-gray-300 bg-white py-2 px-4 text-sm font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">Annuler</a>
                            <button type="submit" class="ml-3 inline-flex justify-center rounded-md border border-transparent bg-indigo-600 py-2 px-4 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">Sauver</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>