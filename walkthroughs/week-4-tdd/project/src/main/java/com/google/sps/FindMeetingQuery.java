// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;


public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    Collection<String> meetingAttendees = request.getAttendees();
    long meetingDuration = request.getDuration();
    int dayStart = TimeRange.WHOLE_DAY.start();
    int dayEnd = TimeRange.WHOLE_DAY.end();
    boolean[] availableMinutes = new boolean[dayEnd - dayStart];
    Arrays.fill(availableMinutes, Boolean.TRUE);
    Collection<TimeRange> possibleTimings = new ArrayList<TimeRange>();

    // Check if meeting request duration is longer than a day. If it is, return empty list
    if(request.getDuration() > TimeRange.WHOLE_DAY.duration()) {
      return possibleTimings;
    }
      
    for(Event event : events) {
      TimeRange eventTimeRange = event.getWhen();
      Set<String> eventAttendees = event.getAttendees();
      boolean toInclude = false;
      // Check if attendees of the meeting is involved in this event. 
      // If it is, block out the minutes in the availableMinutes array.
      // Otherwise, ignore
      for(String attendee : meetingAttendees) {
        if(eventAttendees.contains(attendee)) {
          toInclude = true;
          break;
        }
      }
      if(toInclude) {
        int eventStart = eventTimeRange.start();
        int eventEnd = eventTimeRange.end();
        for(int i = eventStart; i < eventEnd; i++) {
          availableMinutes[i] = false;
        }
      }
    }
    // Iterate through availableMinutes array to find available timings.
    // Check if the duration of the available timings are longer than the meeting request.
    int startTime = 0;
    while(startTime < availableMinutes.length) {
      if (availableMinutes[startTime]) {
        int duration = 0;
        while(startTime + duration < availableMinutes.length && availableMinutes[startTime + duration]) {
          duration ++;
        }
        if(duration >= meetingDuration) {
          possibleTimings.add(TimeRange.fromStartDuration(dayStart + startTime, duration));
        }
        startTime += duration;
      } else {
        startTime ++;
      }
    }
    return possibleTimings;
  }
}
