const apiUrl = 'http://localhost:8080/api/v1/detailOrder/';
const orderApiUrl = 'http://localhost:8080/api/v1/orders/';
const productApiUrl = 'http://localhost:8080/api/v1/products/';

// Listar detalles
async function fetchDetails() {
  try {
    const response = await fetch(apiUrl);
    if (!response.ok) throw new Error('Error al listar detalles');
    const data = await response.json();
    renderDetails(data);
  } catch (error) {
    console.error('Error:', error);
    alert('Error al cargar los detalles');
  }
}

// Mostrar detalles en la tabla
function renderDetails(details) {
  const tableBody = document.getElementById('detail-table-body');
  tableBody.innerHTML = '';
  details.forEach(detail => {
    const row = `
      <tr>
        <td>${detail.idDetailOrder}</td>
        <td>${detail.order ? detail.order.id_orders : 'Sin pedido'}</td>
        <td>${detail.products ? detail.products.id_products : 'Sin producto'}</td>
        <td>${detail.amount}</td>
        <td class="text-center">
          <button class="btn btn-primary btn-sm" onclick="loadDetail(${detail.idDetailOrder})">
            <i class="fas fa-edit"></i>
          </button>
          <button class="btn btn-danger btn-sm" onclick="removeDetail(${detail.idDetailOrder})">
            <i class="fas fa-trash"></i>
          </button>
        </td>
      </tr>
    `;
    tableBody.innerHTML += row;
  });
}

// Crear o actualizar detalle
document.getElementById('detail-form').addEventListener('submit', function (event) {
  event.preventDefault();
  const id = document.getElementById('detail-id').value;
  const orderId = document.getElementById('orderId').value;
  const productId = document.getElementById('productId').value;
  const amount = document.getElementById('quantity').value;

  if (!orderId || !productId || !amount) {
    alert('Todos los campos son obligatorios.');
    return;
  }

  const detail = {
    amount: parseInt(amount),
    order: { id_orders: parseInt(orderId) },
    products: { id_products: parseInt(productId) }
  };

  if (id) {
    updateDetail(id, detail);
  } else {
    createDetail(detail);
  }
});

// Crear detalle
async function createDetail(detail) {
  try {
    const response = await fetch(apiUrl, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(detail)
    });
    if (!response.ok) {
      const errorText = await response.text();
      console.error('Error del servidor:', errorText);
      throw new Error('Error al crear detalle');
    }
    alert('Detalle creado exitosamente');
    document.getElementById('detail-form').reset();
    fetchDetails();
  } catch (error) {
    console.error('Error:', error);
    alert('Error al crear detalle. Verifica los campos.');
  }
}

// Actualizar detalle
async function updateDetail(id, detail) {
  try {
    const response = await fetch(apiUrl + id, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(detail)
    });
    if (!response.ok) throw new Error('Error al actualizar detalle');
    alert('Detalle actualizado correctamente');
    document.getElementById('detail-form').reset();
    fetchDetails();
  } catch (error) {
    console.error('Error:', error);
    alert('Error al actualizar detalle. Verifica los campos.');
  }
}

// Eliminar detalle
async function removeDetail(id) {
  if (!confirm('¿Estás seguro de eliminar este detalle?')) return;
  try {
    const response = await fetch(apiUrl + id, { method: 'DELETE' });
    if (response.ok) {
      alert('Detalle eliminado correctamente');
      fetchDetails();
    } else if (response.status === 404) {
      alert('Detalle no encontrado para eliminar');
    } else {
      throw new Error('Error al eliminar detalle: ' + response.status);
    }
  } catch (error) {
    console.error('Error:', error);
    alert('Error al eliminar detalle. Verifique si el detalle existe.');
  }
}

// Cargar detalle en formulario
async function loadDetail(id) {
  try {
    const response = await fetch(apiUrl + id);
    if (!response.ok) throw new Error('Error al obtener detalle');
    const detail = await response.json();
    document.getElementById('detail-id').value = detail.idDetailOrder;
    document.getElementById('orderId').value = detail.order ? detail.order.id_orders : '';
    document.getElementById('productId').value = detail.products ? detail.products.id_products : '';
    document.getElementById('quantity').value = detail.amount;
  } catch (error) {
    console.error('Error:', error);
    alert('Error al cargar el detalle.');
  }
}

// Buscar detalles
async function searchDetails() {
  const searchTerm = document.getElementById('search-input').value.trim().toLowerCase();
  try {
    const response = await fetch(apiUrl);
    if (!response.ok) throw new Error('Error al obtener detalles');
    const details = await response.json();
    const filtered = details.filter(detail =>
      detail.idDetailOrder.toString().includes(searchTerm) ||
      (detail.order && detail.order.id_orders.toString().includes(searchTerm)) ||
      (detail.products && detail.products.id_products.toString().includes(searchTerm))
    );
    renderDetails(filtered);
  } catch (error) {
    console.error('Error:', error);
    alert('Error al buscar detalles.');
  }
}

// Botón de búsqueda
document.getElementById('search-btn').addEventListener('click', searchDetails);

// Cargar pedidos en select
async function loadOrders() {
  try {
    const response = await fetch(orderApiUrl);
    if (!response.ok) throw new Error('Error al listar pedidos');
    const orders = await response.json();
    const ordersSelect = document.getElementById('orderId');
    ordersSelect.innerHTML = '<option value="">Seleccione un pedido</option>';
    orders.forEach(order => {
      const option = document.createElement('option');
      option.value = order.id_orders;
      option.textContent = `Pedido #${order.id_orders}`;
      ordersSelect.appendChild(option);
    });
  } catch (error) {
    console.error('Error:', error);
    alert('Error al cargar los pedidos.');
  }
}

// Cargar productos en select
async function loadProducts() {
  try {
    const response = await fetch(productApiUrl);
    if (!response.ok) throw new Error('Error al listar productos');
    const products = await response.json();
    const productsSelect = document.getElementById('productId');
    productsSelect.innerHTML = '<option value="">Seleccione un producto</option>';
    products.forEach(product => {
      const option = document.createElement('option');
      option.value = product.id_products;
      option.textContent = product.name || `Producto #${product.id_products}`;
      productsSelect.appendChild(option);
    });
  } catch (error) {
    console.error('Error:', error);
    alert('Error al cargar los productos.');
  }
}

// Inicializar
window.onload = () => {
  fetchDetails();
  loadOrders();
  loadProducts();
};
