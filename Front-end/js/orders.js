// orders.js
const apiUrl = 'http://localhost:8080/api/v1/orders/';
const customerApiUrl = 'http://localhost:8080/api/v1/customers/';
const employeeApiUrl = 'http://localhost:8080/api/v1/employees/';

// Listar pedidos
async function fetchOrders() {
  try {
    const response = await fetch(apiUrl);
    if (!response.ok) throw new Error('Error al listar pedidos');

    const data = await response.json();
    renderOrders(data);
  } catch (error) {
    console.error('Error:', error);
    alert('Error al cargar los pedidos');
  }
}

// Mostrar pedidos en la tabla
function renderOrders(orders) {
  const tableBody = document.getElementById('order-table-body');
  tableBody.innerHTML = '';

  orders.forEach(order => {
    const row = `
      <tr>
        <td>${order.id_orders}</td>
        <td>${order.customer?.name || ''}</td>
        <td>${order.employees?.name || ''}</td>
        <td>${order.orderDate}</td>
        <td class="text-center">
          <button class="btn btn-primary btn-sm" onclick="loadOrder(${order.id_orders})">
            <i class="fas fa-edit"></i>
          </button>
          <button class="btn btn-danger btn-sm" onclick="removeOrder(${order.id_orders})">
            <i class="fas fa-trash"></i>
          </button>
        </td>
      </tr>
    `;
    tableBody.innerHTML += row;
  });
}

// Crear pedido
async function createOrder(order) {
  try {
    const response = await fetch(apiUrl, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(order)
    });
    if (!response.ok) throw new Error('Error al crear pedido');

    alert('Pedido creado exitosamente');
    fetchOrders();
    document.getElementById('order-form').reset();
  } catch (error) {
    console.error('Error:', error);
    alert('Error al crear pedido. Verifica los campos.');
  }
}

// Cargar pedido en formulario
async function loadOrder(id) {
  try {
    const response = await fetch(apiUrl + id);
    if (!response.ok) throw new Error('Error al obtener pedido');

    const order = await response.json();
    document.getElementById('order-id').value = order.id_orders;
    document.getElementById('customer').value = order.customer.id_customers;
    document.getElementById('employees').value = order.employees.id_employees;
    document.getElementById('orderDate').value = order.orderDate;
  } catch (error) {
    console.error('Error:', error);
    alert('Error al cargar el pedido.');
  }
}

// Actualizar pedido
async function updateOrder(id, order) {
  try {
    const response = await fetch(apiUrl + id, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(order)
    });
    if (!response.ok) throw new Error('Error al actualizar pedido');

    alert('Pedido actualizado correctamente');
    fetchOrders();
    document.getElementById('order-form').reset();
  } catch (error) {
    console.error('Error:', error);
    alert('Error al actualizar pedido. Verifica los campos.');
  }
}

// Eliminar pedido
async function removeOrder(id) {
  if (!confirm('¿Estás seguro de eliminar este pedido?')) return;

  try {
    const response = await fetch(apiUrl + id, { method: 'DELETE' });
    if (!response.ok) throw new Error('Error al eliminar pedido');

    alert('Pedido eliminado correctamente');
    fetchOrders();
  } catch (error) {
    console.error('Error:', error);
    alert('Error al eliminar pedido.');
  }
}

// Cargar clientes
async function loadCustomers() {
  try {
    const response = await fetch(customerApiUrl);
    if (!response.ok) throw new Error('Error al listar clientes');

    const customers = await response.json();
    const customerSelect = document.getElementById('customer');
    customerSelect.innerHTML = '<option value="">Seleccione un cliente</option>';

    customers.forEach(customer => {
      const option = document.createElement('option');
      option.value = customer.id_customers;
      option.textContent = customer.name;
      customerSelect.appendChild(option);
    });
  } catch (error) {
    console.error('Error:', error);
    alert('Error al cargar los clientes.');
  }
}

// Cargar empleados
async function loadEmployees() {
  try {
    const response = await fetch(employeeApiUrl);
    if (!response.ok) throw new Error('Error al listar empleados');

    const employees = await response.json();
    const employeeSelect = document.getElementById('employees');
    employeeSelect.innerHTML = '<option value="">Seleccione un empleado</option>';

    employees.forEach(employee => {
      const option = document.createElement('option');
      option.value = employee.id_employees;
      option.textContent = employee.name;
      employeeSelect.appendChild(option);
    });
  } catch (error) {
    console.error('Error:', error);
    alert('Error al cargar los empleados.');
  }
}

// Evento enviar formulario
document.getElementById('order-form').addEventListener('submit', function (event) {
  event.preventDefault();

  const id = document.getElementById('order-id').value;
  const customerId = document.getElementById('customer').value;
  const employeeId = document.getElementById('employees').value;
  const orderDate = document.getElementById('orderDate').value;

  if (!customerId || !employeeId || !orderDate) {
    alert('Todos los campos son obligatorios.');
    return;
  }

  const order = {
    orderDate,
    customer: { id_customers: customerId },
    employees: { id_employees: employeeId }
  };

  if (id) {
    updateOrder(id, order);
  } else {
    createOrder(order);
  }
});

// Buscar pedidos
document.getElementById('searchOrder').addEventListener('input', function () {
  const searchValue = this.value.toLowerCase();
  const rows = document.querySelectorAll('#order-table-body tr');

  rows.forEach(row => {
    const text = row.innerText.toLowerCase();
    row.style.display = text.includes(searchValue) ? '' : 'none';
  });
});

// Inicializar página
window.onload = () => {
  fetchOrders();
  loadCustomers();
  loadEmployees();
};
