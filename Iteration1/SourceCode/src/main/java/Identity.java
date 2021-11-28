public class Identity {
    private int ID;

    public Identity(int order) {
        this.ID = createID(order);

    }

    public void ID(int ID){

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public int createID(int order){
        return order+150110000;
    }
}
