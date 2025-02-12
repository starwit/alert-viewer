import CrudRest from "./CrudRest";

class AlertRest extends CrudRest {
    constructor() {
        super(window.location.pathname + "api/alerts");
    }

}
export default AlertRest;