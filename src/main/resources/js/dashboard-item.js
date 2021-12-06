define('dashboard-items/item', ['underscore', 'jquery', 'wrm/context-path'], function (_, $, contextPath) {
    let DashboardItem = function (API) {
        this.API = API;
        this.projects = [];
        this.projectsList = [];
    };
    /**
     * Called to render the view for a fully configured dashboard item.
     *
     * @param context The surrounding <div/> context that this items should render into.
     * @param preferences The user preferences saved for this dashboard item (e.g. filter id, number of results...)
     */
    DashboardItem.prototype.render = function (context, preferences) {

        this.API.showLoadingBar();
        let $element = this.$element = $(context).find("#dynamic-content");
        let self = this;
        this.requestData(preferences).done(function (data) {
            self.API.hideLoadingBar();
            self.projects = data;

            $.each(data, function (index, value) {
                console.log("index->" + index + " value-> " + value);
            })


            if (self.projects === undefined || self.projects.length === 0) {
                $element.empty().html(soy.dashboardItem.templates.Empty());
            } else {
                $element.empty().html(soy.dashboardItem.templates.ProjectsList({projects: self.projects}));
            }
            self.API.resize();
            $element.find(".submit").click(function (event) {
                event.preventDefault();
                self.render(context, preferences);
            });
        });
    };

    DashboardItem.prototype.requestData = function (preferences) {

        return $.ajax({
            method: "POST",
            contentType: "application/json",
            dataType: "json",
            //data: JSON.stringify(preferences['projects-list']),
            data: preferences['projects-list'],
            url: contextPath() + "/rest/rest-resource/1.0/plugin-rest/get",
            async: false,
            cache: false,
        });
    };

    DashboardItem.prototype.renderEdit = function (context, preferences) {
        let $element = this.$element = $(context).find("#dynamic-content");
        let self = this;

        self.projectsList = getRequestProjectsList();
        $element.empty().html(soy.dashboardItem.templates.Configuration({projectsList: self.projectsList}));

        this.API.once("afterRender", this.API.resize);
        let $form = $("form", $element);
        $(".cancel", $form).click(_.bind(function () {
            if (preferences['projects-list'])
                this.API.closeEdit();
        }, this));

        $form.submit(_.bind(function (event) {
            event.preventDefault();
            let preferences = getPreferencesFromForm($form);
            if (preferences['projects-list'] !== undefined && preferences['projects-list'].length  !== 0) {
                this.API.savePreferences(preferences);
                this.API.showLoadingBar();
            }
        }, this));
    };

    function getPreferencesFromForm($form) {
        let preferencesArray = $form.serializeArray();
        let preferencesObject = {};
        let array = [];
        preferencesArray.forEach(function (element) {
            array.push(element.name);
        });
        preferencesObject['projects-list'] = array;

        return preferencesObject;
    }

    function getRequestProjectsList() {
        let responce = [];
        $.ajax({
            url: contextPath() + "/rest/rest-resource/1.0/plugin-rest/projects-list",
            method: "GET",
            async: false,
            cache: false,
            success: function (data) {
                responce = data;
            }
        });

        return responce;
    }

    return DashboardItem;
});