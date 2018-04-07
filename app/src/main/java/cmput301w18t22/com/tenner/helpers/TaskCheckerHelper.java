package cmput301w18t22.com.tenner.helpers;

public class TaskCheckerHelper {

    public boolean checkTitle(String title) {
        // Title length constraint is currently <= 30
        if (title.length() > 30) {
            return false;
        } else return true;
    }

    public boolean checkDuplicateTitle(String title) {
        //TODO: Get current user
        /*User currentUser = ...
        for (int i; i < currentUser.getRequestedTasks().size(); i++) {
            if (currentUser.getRequestedTasks().get(i).getTitle() == title) ;
            return false;
        }*/
        return true;
    }

    public boolean checkDescription(String description) {
        // Description length constraint is currently <= 300
        if (description.length() > 300) {
            return false;
        } else return true;
    }

    public boolean checkAddress(String address) {
        // This always passes for now, but we should verify it's an address eventually
        return true;
    }

}
