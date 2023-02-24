<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calendar</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://unpkg.com/@tailwindcss/forms@0.2.1/dist/forms.min.css" rel="stylesheet">
    <link rel="dns-prefetch" href="//unpkg.com" />
    <link rel="dns-prefetch" href="//cdn.jsdelivr.net" />
    <link rel="stylesheet" href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css">
    <script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.js" defer></script>

    <style>
        [x-cloak] {
            display: none;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="my-5 mx-auto px-4 sm:px-6 lg:px-8 w-full xl:w-2/3">
    <div>
        <div class="antialiased sans-serif">
            <div x-data="app()" x-init="[initDate(), getNoOfDays()]" x-cloak>
                <div class="container mx-auto px-4 py-2 md:py-24">
                    <div class="bg-white rounded-lg shadow overflow-hidden">
                        <div class="flex items-center justify-between py-2 px-6">
                            <div>
                                <span x-text="MONTH_NAMES[month]" class="text-lg font-bold text-gray-800"></span>
                                <span x-text="year" class="ml-1 text-lg text-gray-600 font-normal"></span>
                            </div>
                            <div class="border rounded-lg px-1" style="padding-top: 2px;">
                                <button
                                        type="button"
                                        class="leading-none rounded-lg transition ease-in-out duration-100 inline-flex cursor-pointer hover:bg-gray-200 p-1 items-center"
                                        :class="{'cursor-not-allowed opacity-25': month == 0 }"
                                        :disabled="month == 0 ? true : false"
                                        @click="month--; getNoOfDays()">
                                    <svg class="h-6 w-6 text-gray-500 inline-flex leading-none"  fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"/>
                                    </svg>
                                </button>
                                <div class="border-r inline-flex h-6"></div>
                                <button
                                        type="button"
                                        class="leading-none rounded-lg transition ease-in-out duration-100 inline-flex items-center cursor-pointer hover:bg-gray-200 p-1"
                                        :class="{'cursor-not-allowed opacity-25': month == 11 }"
                                        :disabled="month == 11 ? true : false"
                                        @click="month++; getNoOfDays()">
                                    <svg class="h-6 w-6 text-gray-500 inline-flex leading-none"  fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"/>
                                    </svg>
                                </button>
                            </div>
                        </div>
                        <div class="-mx-1 -mb-1">
                            <div class="flex flex-wrap" style="margin-bottom: -40px;">
                                <template x-for="(day, index) in DAYS" :key="index">
                                    <div style="width: 14.26%" class="px-2 py-2">
                                        <div
                                            x-text="day"
                                            class="text-gray-600 text-sm uppercase tracking-wide font-bold text-center"></div>
                                    </div>
                                </template>
                            </div>
                            <div class="flex flex-wrap border-t border-l">
                                <template x-for="blankday in blankdays">
                                    <div
                                        style="width: 14.28%; height: 120px"
                                        class="text-center border-r border-b px-4 pt-2"
                                    ></div>
                                </template>
                                <template x-for="(date, dateIndex) in no_of_days" :key="dateIndex">
                                    <div style="width: 14.28%; height: 120px" class="px-4 pt-2 border-r border-b relative">
                                        <div
                                                @click="showEventModal(date)"
                                                x-text="date"
                                                class="inline-flex w-6 h-6 items-center justify-center cursor-pointer text-center leading-none rounded-full transition ease-in-out duration-100"
                                                :class="{'bg-blue-500 text-white': isToday(date) == true, 'text-gray-700 hover:bg-blue-200': isToday(date) == false }"
                                        ></div>
                                        <div style="height: 80px;" class="overflow-y-auto mt-1">
                                            <template x-for="event in events.filter(e => new Date(e.event_date).toDateString() ===  new Date(year, month, date).toDateString() )">
                                                <div
                                                        class="px-2 py-1 rounded-lg mt-1 overflow-hidden border"
                                                        :class="{
												'border-blue-200 text-blue-800 bg-blue-100': event.event_theme === 'blue',
												'border-red-200 text-red-800 bg-red-100': event.event_theme === 'red',
												'border-yellow-200 text-yellow-800 bg-yellow-100': event.event_theme === 'yellow',
												'border-green-200 text-green-800 bg-green-100': event.event_theme === 'green',
												'border-purple-200 text-purple-800 bg-purple-100': event.event_theme === 'purple'
											}"
                                                >
                                                    <p x-text="event.event_title" class="text-sm truncate leading-tight"></p>
                                                </div>
                                            </template>
                                        </div>
                                    </div>
                                </template>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Modal -->
                <div style=" background-color: rgba(0, 0, 0, 0.8)" class="fixed z-40 top-0 right-0 left-0 bottom-0 h-full w-full" x-show.transition.opacity="openEventModal">
                    <div class="p-4 max-w-xl mx-auto relative absolute left-0 right-0 overflow-hidden mt-24">
                        <div class="shadow absolute right-0 top-0 w-10 h-10 rounded-full bg-white text-gray-500 hover:text-gray-800 inline-flex items-center justify-center cursor-pointer"
                             x-on:click="openEventModal = !openEventModal">
                            <svg class="fill-current w-6 h-6" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                                <path
                                        d="M16.192 6.344L11.949 10.586 7.707 6.344 6.293 7.758 10.535 12 6.293 16.242 7.707 17.656 11.949 13.414 16.192 17.656 17.606 16.242 13.364 12 17.606 7.758z" />
                            </svg>
                        </div>

                        <div class="shadow w-full rounded-lg bg-white overflow-hidden w-full block p-8">
                            <form class="space-y-8 divide-y divide-gray-200" action="${pageContext.request.contextPath}/rv/add" method="post">
                                <div class="space-y-8 divide-y divide-gray-200 sm:space-y-5">
                                    <div class="space-y-6 sm:space-y-5">
                                        <div>
                                            <h3 class="text-lg font-medium leading-6 text-gray-900">Ajouter un rendez-vous</h3>
                                        </div>

                                        <div class="space-y-6 sm:space-y-5">
                                            <div class="sm:grid sm:grid-cols-3 sm:items-start sm:gap-4 sm:border-t sm:border-gray-200 sm:pt-5">
                                                <label for="client_id" class="block text-sm font-medium text-gray-700 p-2">Client</label>
                                                <div class="mt-1">
                                                    <select name="client_id" id="client_id" class="mt-1 block w-full rounded-md border-gray-300 py-2 pl-3 pr-10 text-base focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 sm:text-sm" required>
                                                        <c:forEach items="${clients}" var="client">
                                                            <option value="${client.getId()}">${client.getPrenom()} ${client.getNom()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="sm:grid sm:grid-cols-3 sm:items-start sm:gap-4 sm:border-t sm:border-gray-200 sm:pt-5">
                                                <label for="creneau_id" class="block text-sm font-medium text-gray-700 p-2">Créneaux</label>
                                                <div class="mt-1">
                                                    <select name="creneau_id" id="creneau_id" class="mt-1 block w-full rounded-md border-gray-300 py-2 pl-3 pr-10 text-base focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 sm:text-sm" required>
                                                        <c:forEach items="${creneaux}" var="creneau">
                                                            <option value="${creneau.getId()}">${creneau.getHeureDebut()} - ${creneau.getHeureFin()} (${creneau.getMedecin().getNomComplet()})</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="sm:grid sm:grid-cols-3 sm:items-start sm:gap-4 sm:border-t sm:border-gray-200 sm:pt-5">
                                                <label for="jour" class="block text-sm font-medium text-gray-700 p-2">Jour du rendez-vous</label>
                                                <div class="mt-1">
                                                    <input type="date" name="jour" id="jour" class="p-2 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" placeholder="Jour" required>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="pt-5">
                                            <div class="flex justify-end">
                                                <a href="${pageContext.request.contextPath}/calendar/index" type="button" class="rounded-md border border-gray-300 bg-white py-2 px-4 text-sm font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">Annuler</a>
                                                <button type="submit" class="ml-3 inline-flex justify-center rounded-md border border-transparent bg-indigo-600 py-2 px-4 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">Sauver</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- /Modal -->
            </div>
        </div>
    </div>
</div>
<script>
    var rvs = [];
    let dateComponents = "";
    let year = "";
    let month = "";
    let day = "";
    let d = "";
    <c:forEach items="${rvs}" var="rv">
        dateComponents = "${rv.getJourString()}".split('-');
        year = parseInt(dateComponents[0], 10);
        month = parseInt(dateComponents[1], 10) - 1;
        day = parseInt(dateComponents[2], 10);
        d = new Date(year, month, day);
        theme = d > new Date() ? 'blue' : 'red';
        theme = d == new Date() ? 'green': theme;
        console.log(d);
        console.log(new Date());
        console.log('_______________');
        var rvObj = {
            event_date: d,
            event_title: "${rv.getCreneau().getMedecin().getNomComplet()} : ${rv.getClient().getNomComplet()}",
            event_theme: theme
        };
        rvs.push(rvObj);
    </c:forEach>
        const MONTH_NAMES = ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'];
        const DAYS = ['Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam'];

        function app() {
            return {
                month: '',
                year: '',
                no_of_days: [],
                blankdays: [],
                days: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],

                events: rvs,
                event_title: '',
                event_date: '',
                event_theme: 'blue',

                themes: [
                    {
                        value: "blue",
                        label: "Blue Theme"
                    },
                    {
                        value: "red",
                        label: "Red Theme"
                    },
                    {
                        value: "yellow",
                        label: "Yellow Theme"
                    },
                    {
                        value: "green",
                        label: "Green Theme"
                    },
                    {
                        value: "purple",
                        label: "Purple Theme"
                    }
                ],

                openEventModal: false,

                initDate() {
                    let today = new Date();
                    this.month = today.getMonth();
                    this.year = today.getFullYear();
                    this.datepickerValue = new Date(this.year, this.month, today.getDate()).toDateString();
                },

                isToday(date) {
                    const today = new Date();
                    const d = new Date(this.year, this.month, date);

                    return today.toDateString() === d.toDateString() ? true : false;
                },

                showEventModal(date) {
                    this.openEventModal = true;
                    const input = document.getElementById('jour');
                    input.value = date+"/"+ this.month+"/"+this.year;
                },

                getNoOfDays() {
                    let daysInMonth = new Date(this.year, this.month + 1, 0).getDate();

                    // find where to start calendar day of week
                    let dayOfWeek = new Date(this.year, this.month).getDay();
                    let blankdaysArray = [];
                    for ( var i=1; i <= dayOfWeek; i++) {
                        blankdaysArray.push(i);
                    }

                    let daysArray = [];
                    for ( var i=1; i <= daysInMonth; i++) {
                        daysArray.push(i);
                    }

                    this.blankdays = blankdaysArray;
                    this.no_of_days = daysArray;
                }
            }
        }
    </script>
</body>
</html>