package main.java.controller;

import java.io.IOException;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.model.Template;
import main.java.model.User;
import main.java.session.SessionManager;
import main.java.service.implement.TemplateServiceImpl;
import main.java.service.TemplateDetailSerice;
import main.java.service.implement.TemplateDetailServiceImp;

public class UserTemplateController extends BaseController {
	
    @FXML
    private TableView<Template> templateTableView; // Bảng chứa danh sách template

    @FXML
    private TableColumn<Template, String> titleColumn; // Cột tiêu đề

    @FXML
    private TableColumn<Template, String> descriptionColumn; // Cột mô tả

    @FXML
    private TableColumn<Template, Integer> viewVolColumn; // Cột số lượt xem

    @FXML
    private TableColumn<Template, Integer> saveVolColumn; // Cột số lượt lưu

    @FXML
    private TextField searchField; // Ô tìm kiếm

    @FXML
    private HBox paginationButtons; // HBox chứa các nút phân trang
    
    private User currentUser = new User();

    private final TemplateServiceImpl templateService = new TemplateServiceImpl(); // Service quản lý template
    private final TemplateDetailSerice templateDetailService = new TemplateDetailServiceImp();
    private final ObservableList<Template> templateList = FXCollections.observableArrayList(); // Danh sách template đầy đủ
    public static Template uesrSelectedTemplate;
    private FilteredList<Template> filteredList; // Danh sách template đã lọc

    private final ObservableList<Template> currentPageList = FXCollections.observableArrayList(); // Danh sách hiển thị ở trang hiện tại
    private int currentPage = 1; // Trang hiện tại
    private final int rowsPerPage = 12; // Số hàng trên mỗi trang

    @FXML
    public void initialize() {
    	currentUser = SessionManager.getInstance().getCurrentUser();
        setupTableColumns();
        loadTemplatesIntoTableView();
        setupSearch();
    }

    private void setupTableColumns() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("templateTitle"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("templateDes"));
        viewVolColumn.setCellValueFactory(new PropertyValueFactory<>("viewCount"));
        saveVolColumn.setCellValueFactory(new PropertyValueFactory<>("saveCount"));
    }

    private void loadTemplatesIntoTableView() {
        List<Template> templates = templateService.getAllTemplates();
        templateList.setAll(templates);

        // Tạo danh sách đã lọc
        filteredList = new FilteredList<>(templateList, b -> true);

        // Hiển thị dữ liệu trang đầu tiên
        setupPagination();
    }

    private void setupSearch() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(template -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                return template.getTemplateTitle().toLowerCase().contains(lowerCaseFilter)
                        || template.getTemplateDes().toLowerCase().contains(lowerCaseFilter);
            });

            // Reset về trang đầu tiên khi tìm kiếm thay đổi
            currentPage = 1;
            setupPagination();
        });
    }

    private void setupPagination() {
        // Tính toán số lượng trang
        int totalRows = filteredList.size();
        int totalPages = (int) Math.ceil((double) totalRows / rowsPerPage);

        if (currentPage > totalPages) {
            currentPage = totalPages;
        }
        if (currentPage < 1) {
            currentPage = 1;
        }

        int fromIndex = (currentPage - 1) * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, totalRows);

        currentPageList.setAll(filteredList.subList(fromIndex, toIndex));
        templateTableView.setItems(currentPageList);
    }

    // Các phương thức phân trang
    @FXML
    void goToFirstPage(ActionEvent event) {
        currentPage = 1;
        setupPagination();
    }

    @FXML
    void goToSecondPage(ActionEvent event) {
        currentPage = 2;
        setupPagination();
    }

    @FXML
    void goToThirdPage(ActionEvent event) {
        currentPage = 3;
        setupPagination();
    }

    @FXML
    void goToPreviousPage(ActionEvent event) {
        if (currentPage > 1) {
            currentPage--;
            setupPagination();
        }
    }

    @FXML
    void goToNextPage(ActionEvent event) {
        int totalPages = (int) Math.ceil((double) filteredList.size() / rowsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            setupPagination();
        }
    }

    @FXML
    void goToLastPage(ActionEvent event) {
        currentPage = (int) Math.ceil((double) filteredList.size() / rowsPerPage);
        setupPagination();
    }
    
    @FXML
    void viewBtnEnter(ActionEvent event) throws IOException {
    	uesrSelectedTemplate = templateTableView.getSelectionModel().getSelectedItem();
    	switchToUserTemplateView(event);
    }
    
    @FXML
    void saveBtnEnter(ActionEvent event) {
    	System.out.println(1);
    	uesrSelectedTemplate = templateTableView.getSelectionModel().getSelectedItem();
    	String temText = templateDetailService.getTemplateTextByTemplate(uesrSelectedTemplate);
    	String temSetsumei = templateDetailService.getTemplateSetsumeiByTemplate(uesrSelectedTemplate);
    	templateDetailService.createTemplateDetail(currentUser.getId(), uesrSelectedTemplate.getTemplateTitle(), uesrSelectedTemplate.getTemplateDes(), temText, temSetsumei);
    }

    // Các phương thức điều hướng
    @FXML
    void logOut(MouseEvent event) throws IOException {
        switchToLogin(event);
    }

    @FXML
    void settingClicked(MouseEvent event) throws IOException {
        switchToUserSetting(event);
    }

    @FXML
    void templateSaveClicked(MouseEvent event) throws IOException {
        switchToUserTemplateSave(event);
    }

    @FXML
    void userHomepageClicked(MouseEvent event) throws IOException {
        switchToUserHomepage(event);
    }

    @FXML
    void userTemplateClicked(MouseEvent event) throws IOException {
        switchToUserTemplate(event);
    }

    @FXML
    void userProfileClicked(MouseEvent event) throws IOException {
        switchToUserProfile(event);
    }
}
