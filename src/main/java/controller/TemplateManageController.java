package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import main.java.model.Template;
import main.java.service.implement.TemplateServiceImpl;
import main.java.view.AddTemplateView;
import main.java.view.EditTemplateView;

import java.io.IOException;
import java.util.List;

public class TemplateManageController extends BaseController {

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

    private final TemplateServiceImpl templateService = new TemplateServiceImpl(); // Service quản lý template
    private final ObservableList<Template> templateList = FXCollections.observableArrayList(); // Danh sách template đầy đủ
    public static Template selectedTemplate;
    private FilteredList<Template> filteredList; // Danh sách template đã lọc

    private final ObservableList<Template> currentPageList = FXCollections.observableArrayList(); // Danh sách hiển thị ở trang hiện tại
    private int currentPage = 1; // Trang hiện tại
    private final int rowsPerPage = 12; // Số hàng trên mỗi trang

    @FXML
    public void initialize() {
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
    void addTemplateEnter(ActionEvent event) {
        AddTemplateView addTemplateView = new AddTemplateView();
        new AddTemplateController(addTemplateView, templateService);

        addTemplateView.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                templateList.clear();
                templateList.addAll(templateService.getAllTemplates());
                setupPagination();
            }
        });

        addTemplateView.setVisible(true);
    }

    @FXML
    void deleteTemplateEnter(ActionEvent event) {
        deleteTemplate();
    }

    private void deleteTemplate() {
        Template selectedTemplate = templateTableView.getSelectionModel().getSelectedItem();
        if (selectedTemplate != null) {
            templateService.deleteTemplate(selectedTemplate.getTemplateId());
            templateList.remove(selectedTemplate);
            showAlert(AlertType.INFORMATION, "Thành công", "Xóa thành công");
            setupPagination(); // Cập nhật phân trang sau khi xóa
        }
    }

    @FXML
    void editTemplateEnter(ActionEvent event) {
        Template selectedTemplate = templateTableView.getSelectionModel().getSelectedItem();
        if (selectedTemplate != null) {
            EditTemplateView editTemplateView = new EditTemplateView(selectedTemplate.getTemplateTitle(), selectedTemplate.getTemplateDes());
            new EditTemplateController(editTemplateView, templateService, selectedTemplate);

            editTemplateView.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    templateList.clear();
                    templateList.addAll(templateService.getAllTemplates());
                    setupPagination();
                }
            });

            editTemplateView.setVisible(true);
        } else {
            showAlert(AlertType.WARNING, "Chưa chọn template", "Vui lòng chọn template");
        }
    }

    @FXML
    void TemplateManageClick(MouseEvent event) throws IOException {
        switchToTemplateManage(event);
    }

    @FXML
    void adHomepageClick(MouseEvent event) throws IOException {
        switchToAdminHomePage(event);
    }

    @FXML
    void userManageClick(MouseEvent event) throws IOException {
        switchToUserManage(event);
    }
    
    @FXML
    void viewBtnEnter(Event event) throws IOException {
    	selectedTemplate = templateTableView.getSelectionModel().getSelectedItem();
        switchToTemplateView(event);
    }
}
