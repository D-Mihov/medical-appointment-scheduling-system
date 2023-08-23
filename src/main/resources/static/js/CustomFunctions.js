function enableFieldsBasedOnDoctorSelect() {
    $(document).ready(function () {
        $("#appointmentDate").prop("disabled", true);
        $("#appointmentHour").prop("disabled", true);

        $("#doctorSelect").change(function () {
            var selectedDoctorId = $(this).val();
            if (selectedDoctorId !== "") {
                $("#appointmentDate").prop("disabled", false);
            } else {
                $("#appointmentDate").prop("disabled", true);
                $("#appointmentHour").prop("disabled", true);
            }
        });

        $("#appointmentDate").change(function () {
            var selectedDate = $(this).val();
            if (selectedDate !== "") {
                $("#appointmentHour").prop("disabled", false);
            } else {
                $("#appointmentHour").prop("disabled", true);
            }
        });
    });
}

function displayAvailableHoursBasedOnDoctorIdAndDate() {
    $(document).ready(function () {
        $("#appointmentDate").change(function () {
            var selectedDate = $(this).val();
            var selectedDoctorId = $("#doctorSelect").val();

            // Convert the selectedDate to a string in 'YYYY-MM-DD' format
            var formattedDate = new Date(selectedDate).toISOString().split('T')[0];

            console.log(formattedDate + "--" + selectedDate + "--" + selectedDoctorId);
            // Send an AJAX request to fetch available hours
            $.ajax({
                url: "/getAvailableHours", // Change this URL to your backend endpoint
                method: "GET",
                data: {
                    doctorId: selectedDoctorId,
                    appointmentDate: selectedDate
                },
                success: function (response) {
                    // Update the options of the appointmentHour select element
                    var appointmentHourSelect = $("#appointmentHour");
                    appointmentHourSelect.empty();
                    var selectAppointmentHour = $("#selectAppointmentHour").val();
                    appointmentHourSelect.append('<option value="">' + selectAppointmentHour + '</option>');

                    // Loop through the available hours and add them to the select element
                    for (var i = 0; i < response.length; i++) {
                        appointmentHourSelect.append('<option value="' + response[i] + '">' + response[i] + '</option>');
                    }
                },
                error: function () {
                    // Handle errors here
                }
            });
        });
    });
}

function showLoadingOverlayIfFormIsValid() {
    const overlay = document.getElementById("loading-overlay-modal");
    var formIsValid = true;

    if (!document.getElementById('patientFullName').value ||
        !document.getElementById('patientEmail').value ||
        !document.getElementById('doctorSelect').value ||
        !document.getElementById('appointmentDate').value ||
        !document.getElementById('appointmentHour').value ||
        !document.getElementById('diseases').value
    ) {
        formIsValid = false;
    }

    if (formIsValid) {
        overlay.style.display = "flex";
        document.body.style.overflow = "hidden"; // Prevent scrolling of the background content
    }
}

function hideLoadingOverlay() {
    const overlay = document.getElementById("loading-overlay-modal");
    overlay.style.display = "none";

    document.body.style.overflow = "auto"; // Allow scrolling again
}