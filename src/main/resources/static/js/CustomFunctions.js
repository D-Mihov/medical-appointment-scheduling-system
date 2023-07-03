function confirmDeleteDoctor() {
    return confirm("Are you sure you want to delete this doctor?");
}

function confirmDeletePatient() {
    return confirm("Are you sure you want to delete this patient?");
}

function deleteDoctor(doctorId) {
    console.log('deleteDoctor function called');
    if (confirm("Are you sure you want to delete this doctor?")) {
        fetch('/doctors/' + doctorId + '/delete', {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    console.log('Doctor deleted successfully.');
                    // Perform any additional actions or UI updates
                    // For example, you can remove the deleted doctor row from the table
                } else {
                    console.log('Failed to delete doctor.');
                    // Perform any error handling or display error message
                }
            })
            .catch(error => {
                console.error('An error occurred while deleting the doctor:', error);
            });
    }
}