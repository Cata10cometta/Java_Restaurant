const apiUrl = 'http://localhost:8080/api/v1/products/';

// Función para listar productos
async function fetchProducts() {
  try {
    const response = await fetch(apiUrl);
    if (!response.ok) throw new Error('Error al listar productos');

    const data = await response.json();
    renderProducts(data);
  } catch (error) {
    console.error('Error:', error);
  }
}

// Función para renderizar productos en tabla
function renderProducts(products) {
  const tableBody = document.getElementById('product-table-body');
  tableBody.innerHTML = '';

  products.forEach(product => {
    const row = `
      <tr>
        <td>${product.id_products}</td>
        <td>${product.name}</td>
        <td>${product.description}</td>
        <td>$${parseFloat(product.price).toFixed(2)}</td>
        <td class="text-center">
          <button class="btn btn-primary btn-sm" onclick="loadProduct(${product.id_products})">
            <i class="fas fa-edit"></i>
          </button>
          <button class="btn btn-danger btn-sm" onclick="removeProduct(${product.id_products})">
            <i class="fas fa-trash"></i>
          </button>
        </td>
      </tr>
    `;
    tableBody.innerHTML += row;
  });
}

// Función para registrar producto nuevo
async function createProduct(product) {
  try {
    const response = await fetch(apiUrl, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(product)
    });
    if (!response.ok) throw new Error('Error al crear producto');

    alert('Producto creado exitosamente');
    fetchProducts();
    document.getElementById('product-form').reset();
  } catch (error) {
    console.error('Error:', error);
  }
}

// Función para cargar producto en formulario para editar
async function loadProduct(id) {
  try {
    const response = await fetch(apiUrl + id);
    if (!response.ok) throw new Error('Error al obtener producto');

    const product = await response.json();
    document.getElementById('product-id').value = product.id_products;
    document.getElementById('name').value = product.name;
    document.getElementById('description').value = product.description;
    document.getElementById('price').value = product.price;
  } catch (error) {
    console.error('Error:', error);
  }
}

// Función para actualizar producto
async function updateProduct(id, product) {
  try {
    const response = await fetch(apiUrl + id, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(product)
    });
    if (!response.ok) throw new Error('Error al actualizar producto');

    alert('Producto actualizado correctamente');
    fetchProducts();
    document.getElementById('product-form').reset(); // Limpiar formulario
  } catch (error) {
    console.error('Error:', error);
  }
}

// Función para eliminar producto
async function removeProduct(id) {
  if (!confirm('¿Estás seguro de eliminar este producto?')) return;

  try {
    const response = await fetch(apiUrl + id, { method: 'DELETE' });
    if (!response.ok) throw new Error('Error al eliminar producto');

    alert('Producto eliminado correctamente');
    fetchProducts();
  } catch (error) {
    console.error('Error:', error);
  }
}

// Evento para manejar envío del formulario
document.getElementById('product-form').addEventListener('submit', function (event) {
  event.preventDefault();

  const id = document.getElementById('product-id').value; // Tomar el ID del producto
  const name = document.getElementById('name').value;
  const description = document.getElementById('description').value;
  const price = document.getElementById('price').value;

  // Crear el objeto producto
  const product = { name, description, price };

  if (id) {
    // Si hay un ID, actualizamos el producto
    updateProduct(id, product);
  } else {
    // Si no hay un ID, creamos un producto nuevo
    createProduct(product);
  }
});

// Cargar productos al iniciar
window.onload = fetchProducts;
