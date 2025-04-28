const apiUrl = 'http://localhost:8080/api/v1/employees/'; 

// Función para listar empleados
async function fetchEmployees() {
  try {
    const response = await fetch(apiUrl);
    if (!response.ok) throw new Error('Error al listar empleados');

    const data = await response.json();
    renderEmployees(data);
  } catch (error) {
    console.error('Error:', error);
  }
}

// Función para renderizar empleados en la tabla
function renderEmployees(employees) {
  const tableBody = document.getElementById('employee-table-body');
  tableBody.innerHTML = '';

  employees.forEach(employee => {
    const row = `
      <tr>
        <td>${employee.id_employees}</td>
        <td>${employee.name}</td>
        <td>${employee.role}</td>
        <td>${employee.email}</td>
        <td class="text-center">
          <button class="btn btn-primary btn-sm" onclick="loadEmployee(${employee.id_employees})">
            <i class="fas fa-edit"></i>
          </button>
          <button class="btn btn-danger btn-sm" onclick="removeEmployee(${employee.id_employees})">
            <i class="fas fa-trash"></i>
          </button>
        </td>
      </tr>
    `;
    tableBody.innerHTML += row;
  });
}

// Función para registrar un nuevo empleado
async function createEmployee(employee) {
  try {
    const response = await fetch(apiUrl, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(employee)
    });
    if (!response.ok) throw new Error('Error al crear empleado');

    alert('Empleado creado exitosamente');
    fetchEmployees();
    document.getElementById('employee-form').reset();
  } catch (error) {
    console.error('Error:', error);
  }
}

// Función para cargar empleado en el formulario para editar
async function loadEmployee(id) {
  try {
    const response = await fetch(apiUrl + id);
    if (!response.ok) throw new Error('Error al obtener empleado');

    const employee = await response.json();
    document.getElementById('employee-id').value = employee.id_employees;
    document.getElementById('name').value = employee.name;
    document.getElementById('role').value = employee.role;
    document.getElementById('email').value = employee.email;
  } catch (error) {
    console.error('Error:', error);
  }
}

// Función para actualizar empleado
async function updateEmployee(id, employee) {
  try {
    const response = await fetch(apiUrl + id, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(employee)
    });
    if (!response.ok) throw new Error('Error al actualizar empleado');

    alert('Empleado actualizado correctamente');
    fetchEmployees();
    document.getElementById('employee-form').reset();
  } catch (error) {
    console.error('Error:', error);
  }
}

// Función para eliminar empleado
async function removeEmployee(id) {
  if (!confirm('¿Estás seguro de eliminar este empleado?')) return;

  try {
    const response = await fetch(apiUrl + id, { method: 'DELETE' });
    if (!response.ok) throw new Error('Error al eliminar empleado');

    alert('Empleado eliminado correctamente');
    fetchEmployees();
  } catch (error) {
    console.error('Error:', error);
  }
}

// Evento para manejar el envío del formulario
document.getElementById('employee-form').addEventListener('submit', function (event) {
  event.preventDefault();

  const id = document.getElementById('employee-id').value;
  const name = document.getElementById('name').value;
  const role = document.getElementById('role').value;
  const email = document.getElementById('email').value;

  const employee = { name, role, email };

  if (id) {
    updateEmployee(id, employee);
  } else {
    createEmployee(employee);
  }
});

// Cargar empleados al iniciar
window.onload = fetchEmployees;
