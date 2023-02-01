<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un créneau</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://unpkg.com/@tailwindcss/forms@0.2.1/dist/forms.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="../header.jsp" %>
<div class="my-5 mx-auto px-4 sm:px-6 lg:px-8 w-full xl:w-2/3">
    <div class="sm:flex sm:items-center">
        <form class="space-y-8 divide-y divide-gray-200" action="${pageContext.request.contextPath}/creneau/add"
              method="post">
            <div class="space-y-8 divide-y divide-gray-200 sm:space-y-5">
                <div class="space-y-6 sm:space-y-5">
                    <div>
                        <h3 class="text-lg font-medium leading-6 text-gray-900">Ajouter le creneau d'un médecin</h3>
                    </div>

                    <div class="space-y-6 sm:space-y-5">
                        <div class="sm:grid sm:grid-cols-3 sm:items-start sm:gap-4 sm:border-t sm:border-gray-200 sm:pt-5">
                            <label for="medecins_id" class="block text-sm font-medium text-gray-700 p-2">Medecin</label>
                            <div class="mt-1">
                                <div class="flex justify-center">
                                    <div class="mb-3 xl:w-96">
                                        <select class="form-select appearance-none block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding bg-no-repeat border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                                                name="medecins_id" id="medecins_id" aria-label="Default select example">
                                            <c:forEach items="${medecins}" var="medecin">
                                                <option value="${medecin.getId()}">${medecin.getTitre()} ${medecin.getPrenom()} ${medecin.getNom()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="grid grid-cols-2 gap-4">
                            <div class="form-group mb-6">
                                <input type="text" name="hd_debut" class="form-control block w-full
          px-3
          py-1.5
          text-base
          font-normal
          text-gray-700
          bg-white bg-clip-padding
          border border-solid border-gray-300
          rounded
          transition
          ease-in-out
          m-0
          focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" id=""
                                       aria-describedby="emailHelp123" placeholder="heure">
                            </div>
                            <div class="form-group mb-6">
                                <input type="text" class="form-control
          block
          w-full
          px-3
          py-1.5
          text-base
          font-normal
          text-gray-700
          bg-white bg-clip-padding
          border border-solid border-gray-300
          rounded
          transition
          ease-in-out
          m-0
          focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                                       name="md_debut" aria-describedby="emailHelp124" placeholder="Minute">
                            </div>
                        </div>
                        <div class="grid grid-cols-2 gap-4">
                            <div class="form-group mb-6">
                                <input type="text" name="h_fin" class="form-control block w-full
          px-3
          py-1.5
          text-base
          font-normal
          text-gray-700
          bg-white bg-clip-padding
          border border-solid border-gray-300
          rounded
          transition
          ease-in-out
          m-0
          focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                                       aria-describedby="emailHelp123" placeholder="heure">
                            </div>
                            <div class="form-group mb-6">
                                <input type="text" class="form-control
          block
          w-full
          px-3
          py-1.5
          text-base
          font-normal
          text-gray-700
          bg-white bg-clip-padding
          border border-solid border-gray-300
          rounded
          transition
          ease-in-out
          m-0
          focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" id="exampleInput124"
                                       name="m_fin" aria-describedby="emailHelp124" placeholder="Minute">
                            </div>
                        </div>
                        <div class="sm:grid sm:grid-cols-3 sm:items-start sm:gap-4 sm:border-t sm:border-gray-200 sm:pt-5">
                            <label for="version" class="block text-sm font-medium text-gray-700 p-2">Version</label>
                            <div class="mt-1">
                                <input type="number" name="version" id="version"
                                       class="p-2 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                                       placeholder="Version">
                            </div>
                        </div>
                    </div>

                    <div class="pt-5">
                        <div class="flex justify-end">
                            <a href="${pageContext.request.contextPath}/creneau/" type="button"
                               class="rounded-md border border-gray-300 bg-white py-2 px-4 text-sm font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">Annuler</a>
                            <button type="submit"
                                    class="ml-3 inline-flex justify-center rounded-md border border-transparent bg-indigo-600 py-2 px-4 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">
                                Sauver
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>