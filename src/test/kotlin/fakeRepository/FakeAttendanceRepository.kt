package fakeRepository

import data.repository.AttendanceRepository
import domain.model.Attendance
import domain.model.AttendanceState

class FakeAttendanceRepository : AttendanceRepository {

        private val attendanceList = listOf(
            Attendance.create(
                "m001",
                listOf(
                    AttendanceState.PRESENT,
                    AttendanceState.ABSENT,
                    AttendanceState.PRESENT
                )
            ),
            Attendance.create(
                "m002",
                listOf(
                    AttendanceState.ABSENT,
                    AttendanceState.ABSENT,
                    AttendanceState.PRESENT
                )
            ),
            Attendance.create(
                "m003",
                listOf(
                    AttendanceState.PRESENT,
                    AttendanceState.PRESENT,
                    AttendanceState.PRESENT
                )
            ),
            Attendance.create(
                "m004",
                listOf(
                    AttendanceState.LATE,
                    AttendanceState.ABSENT,
                    AttendanceState.PRESENT
                )
            )
        )
        override fun getAllAttendance(): Result<List<Attendance>> {
            return Result.success(attendanceList)
        }
        override fun getAttendanceByMenteeId(menteeId: String): Result<Attendance?> {
            return Result.success(
                attendanceList.find { it.menteeId == menteeId }
            )
        }
    }