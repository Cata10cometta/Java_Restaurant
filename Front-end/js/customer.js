const apiUrl = 'http://localhost:8080/api/v1/customers/';

// Función para listar clientes
async function fetchcustomer(searchTerm = '') {
  try {
    const response = await fetch(apiUrl);
    if (!response.ok) throw new Error('Error al listar clientes');

    const data = await response.json();

    // Filtrar clientes si hay término de búsqueda
    const filteredCustomers = data.filter(customer => {
      return customer.id_customer.toString().includes(searchTerm) ||
             customer.name.toLowerCase().includes(searchTerm.toLowerCase());
    });

    rendercustomer(filteredCustomers);
  } catch (error) {
    console.error('Error:', error);
  }
}

// Función para renderizar clientes en la tabla
function rendercustomer(customer) {
  const tableBody = document.getElementById('customer-table-body');
  tableBody.innerHTML = '';

  customer.forEach(customer => {
    const row = `
      <tr>
        <td>${customer.id_customer}</td>
        <td>${customer.name}</td>
        <td>${customer.email}</td>
        <td>${customer.phone}</td>
        <td class="text-center">
          <button class="btn btn-primary btn-sm" onclick="loadCustomer(${customer.id_customer})">
            <i class="fas fa-edit"></i>
          </button>
          <button class="btn btn-danger btn-sm" onclick="removeCustomer(${customer.id_customer})">
            <i class="fas fa-trash"></i>
          </button>
        </td>
      </tr>
    `;
    tableBody.innerHTML += row;
  });
}

// Función para registrar un nuevo cliente
async function createCustomer(customer) {
  try {
    const response = await fetch(apiUrl, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(customer)
    });
    if (!response.ok) throw new Error('Error al crear cliente');

    alert('Cliente creado exitosamente');
    fetchcustomer();
    document.getElementById('customer-form').reset();
  } catch (error) {
    console.error('Error:', error);
  }
}

// Función para cargar cliente en el formulario para editar
async function loadCustomer(id) {
  try {
    const response = await fetch(apiUrl + id);
    if (!response.ok) throw new Error('Error al obtener cliente');

    const customer = await response.json();
    document.getElementById('customer-id').value = customer.id_customer;
    document.getElementById('name').value = customer.name;
    document.getElementById('email').value = customer.email;
    document.getElementById('phone').value = customer.phone;
  } catch (error) {
    console.error('Error:', error);
  }
}

// Función para actualizar cliente
async function updateCustomer(id, customer) {
  try {
    const response = await fetch(apiUrl + id, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(customer)
    });
    if (!response.ok) throw new Error('Error al actualizar cliente');

    alert('Cliente actualizado correctamente');
    fetchcustomer();
    document.getElementById('customer-form').reset();
  } catch (error) {
    console.error('Error:', error);
  }
}

// Función para eliminar cliente
async function removeCustomer(id) {
  if (!confirm('¿Estás seguro de eliminar este cliente?')) return;

  try {
    const response = await fetch(apiUrl + id, { method: 'DELETE' });
    if (!response.ok) throw new Error('Error al eliminar cliente');

    alert('Cliente eliminado correctamente');
    fetchcustomer();
  } catch (error) {
    console.error('Error:', error);
  }
}

// Evento para manejar el envío del formulario con validación de teléfono
document.getElementById('customer-form').addEventListener('submit', function (event) {
  event.preventDefault();

  const id = document.getElementById('customer-id').value;
  const name = document.getElementById('name').value;
  const email = document.getElementById('email').value;
  const phone = document.getElementById('phone').value;

  // Validación de teléfono (6 a 10 dígitos)
  const phoneRegex = /^\d{6,10}$/;
  if (!phoneRegex.test(phone)) {
    alert('El teléfono debe tener entre 6 y 10 dígitos numéricos.');
    return;
  }

  const customer = { name, email, phone };

  if (id) {
    updateCustomer(id, customer);
  } else {
    createCustomer(customer);
  }
});

// Evento para manejar búsqueda
document.getElementById('search-btn').addEventListener('click', function () {
  const searchTerm = document.getElementById('search-input').value;
  fetchcustomer(searchTerm);
});

// Cargar clientes al iniciar
window.onload = () => fetchcustomer();

// Restringir la entrada del campo teléfono a solo números
document.getElementById('phone').addEventListener('input', function () {
  this.value = this.value.replace(/\D/g, ''); // Elimina cualquier carácter que no sea número
});
