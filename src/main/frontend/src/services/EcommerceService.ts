class EcommerceService {
  getUserRole() {
    return "CUSTOMER";
  }

  getProducts(pageNo: number, categoryId: number, searchTerm: string) {}
}

export default new EcommerceService();
