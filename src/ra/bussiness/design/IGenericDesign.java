package ra.bussiness.design;

public interface IGenericDesign <T,E> {
    void displayAll();
    void addNew();
    void update();
    void delete();
    T findById(E id);
}
