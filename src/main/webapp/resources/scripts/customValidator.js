jQuery.validator.addMethod("scoreValue", function (value, element) {
    return this.optional(element) || /^(A\+|A0|A-|B\+|B0|B-|C\+|C0|C-|D\+|D0|D-|P|)$/.test(value);
}, "성적을 입력해 주세요(A+,A0...)");