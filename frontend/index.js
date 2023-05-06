// XHR REQUEST

function myfunc() {
  alert("Hello World");
  var xhr = new XMLHttpRequest();
  xhr.open("GET", "http://localhost:8080/marksManager/professors");
  xhr.send(null);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      const items = JSON.parse(xhr.responseText);
      items.forEach((item) => {
        let row = table.insertRow();
        let firstName = row.insertCell(0);
        firstName.innerHTML = item.firstName;

        let lastName = row.insertCell(1);
        lastName.innerHTML = item.lastName;

        let speciality = row.insertCell(2);
        speciality.innerHTML = item.speciality.name;
      });
    }
  };

  const table = document.querySelector("#mytable");
}

document.querySelector("#btn").onclick = myfunc;
