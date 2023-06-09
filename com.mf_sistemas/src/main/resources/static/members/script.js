document.addEventListener('DOMContentLoaded', function () {
    fetchMembers();
});

function fetchMembers() {
    fetch("/Members")
        .then(response => response.json())
        .then(members => {
            displayMembers(members);
        })
        .catch(error => {
            console.error('Error fetching members:', error);
        });
}

function displayMembers(members) {
    const memberTableBody = document.querySelector('#memberTable tbody');
    memberTableBody.innerHTML = '';
    members.forEach(member => {
        const row = document.createElement('tr');

        const memberIdCell = document.createElement('td');
        memberIdCell.textContent = member.memberId;
        row.appendChild(memberIdCell);

        const memberFullName = document.createElement('td');
        memberFullName.textContent = member.fullName;
        row.appendChild(memberFullName);

        const memberShipTypeCell = document.createElement('td');
        memberShipTypeCell.textContent = member.memberShipType;
        row.appendChild(memberShipTypeCell);

        const startDate = document.createElement('td');
        startDate.textContent = member.startDate;
        row.appendChild(startDate);

        const endDate = document.createElement('td');
        endDate.textContent = member.endDate;
        row.appendChild(endDate);

        row.classList.add('table-row-button');
        row.addEventListener('click', () => {
            window.location.href = `member_details/member_details.html?memberId=${member.memberId}`;
        });

        memberTableBody.appendChild(row);
    });
}