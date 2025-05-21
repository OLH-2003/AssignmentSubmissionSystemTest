function Login(form) {
  const email = form.username.value;
  const password = form.password.value;

  const data = {
    email: email,
    password: password
  };

  fetch('http://localhost:8080/api/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  })
  .then(response => response.json())
  .then(data => {
    if (data.status === 'success') {
      window.location.href = '/dashboard';
    } else {
      alert('Invalid email or password');
    }
  })
  .catch((error) => {
    console.error('Error:', error);
  });

  return false;