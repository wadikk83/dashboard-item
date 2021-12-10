define('dashboard-items/item', ['underscore', 'jquery', 'wrm/context-path'], function (_, $, contextPath) {
    class DashboardItem {
        constructor(API) {
            this.API = API;
            this.projects = [];
            this.projectsList = [];
        }

        render(context, preferences) {
            this.API.showLoadingBar();
            let $element = $(context).find("#dynamic-content");
            this.requestData(preferences).done(data => {
                this.API.hideLoadingBar();
                this.projects = data;

                if (this.projects === undefined || this.projects.length === 0) {
                    $element.empty().html(soy.dashboardItem.templates.Empty());
                } else {
                    $element.empty().html(soy.dashboardItem.templates.ProjectsList({projects: this.projects}));
                }
                this.API.resize();
                $element.find(".submit").click(event => {
                    event.preventDefault();
                    this.render(context, preferences);
                });
            });
        }

        requestData(preferences) {
            return $.ajax({
                method: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(preferences),
                url: contextPath() + "/rest/dashboard-item-resource/1.0/project-cf/get-list-projects-with-cf",
                async: false,
                cache: false,
            });
        };

        renderEdit(context, preferences) {
            let $element = $(context).find("#dynamic-content");
            this.projectsList = this.getRequestProjectsList();
            $element.empty().html(soy.dashboardItem.templates.Configuration({projectsList: this.projectsList}));

            this.API.once("afterRender", this.API.resize);
            let $form = $("form", $element);
            $(".cancel", $form).click(_.bind(() => {
                if (preferences['projects-list'])
                    this.API.closeEdit();
            }, this));

            $form.submit(_.bind(event => {
                event.preventDefault();
                const preferences = this.getPreferencesFromForm($form);
                this.API.savePreferences(preferences);
                this.API.showLoadingBar();
            }, this));
        }

        getPreferencesFromForm($form) {
            const preferencesArray = $form.serializeArray();
            let preferencesObject = {};
            preferencesArray.forEach(function (element) {
                preferencesObject[element.name] = element.value;
            });
            return preferencesObject;
        }

        getRequestProjectsList() {
            let responce = [];
            $.ajax({
                url: contextPath() + "/rest/dashboard-item-resource/1.0/project-cf/get-list-projects",
                method: "GET",
                async: false,
                cache: false,
                success: function (data) {
                    responce = data;
                }
            });
            return responce;
        }
    }

    return DashboardItem;
});